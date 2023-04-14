package at.ac.fhcampuswien.car_rental.service.booking;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingResponseDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.UpdateBookingDTO;
import at.ac.fhcampuswien.car_rental.mapper.BookingMapper;
import at.ac.fhcampuswien.car_rental.mapper.CarMapper;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.service.currency_converter.CurrencyConverterService;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import at.ac.fhcampuswien.car_rental.utils.LocalDateUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingServiceImpl implements BookingService {
    @Value("${currency-converter.default-currency}")
    String defaultCurrency;
    final BookingRepository bookingRepository;
    final CarRepository carRepository;
    final BookingMapper bookingMapper;
    final CarMapper carMapper;
    final UserService userService;
    final CurrencyConverterService currencyConverterService;

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> getMyBookings() {
        UserEntity user = userService.getUserEntity(userService.getUserName());

        return bookingRepository.findAllByUserIdEquals(user.getUserId())
                .stream().map(item -> bookingMapper.toDto(item, carMapper.toDto(item.getCar())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingDTO getBookingById(Long bookingId) {
        // TODO: Throw error if searching for Booking which does not belong to User?
        BookingEntity bookingEntity = getBookingEntity(bookingId);
        return bookingMapper.toDto(bookingEntity, carMapper.toDto(bookingEntity.getCar()));
    }

    @Override
    @Transactional
    public CreateBookingResponseDTO createBooking(CreateBookingDTO createBookingDTO) {
        // Check if Car with the Id exists
        CarEntity carEntity = carRepository.findById(createBookingDTO.carId()).orElseThrow(() -> {
                    log.error("Could not find car with id {}, could not perform booking!", createBookingDTO.carId());
                    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find car with id " + createBookingDTO.carId());
                }
        );

        // Check if Car is already booked before proceeding
        List<BookingEntity> savedBookingEntities = bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED);
        savedBookingEntities.forEach(item -> {
            if (LocalDateUtils.isOverlapping(createBookingDTO.bookedFrom(), createBookingDTO.bookedUntil(), item.getBookedFrom(), item.getBookedUntil())) {
                log.error("Car with the id {} is already booked in that timestamp!", createBookingDTO.carId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with the id " + createBookingDTO.carId() + " is already booked in the timespan!");
            }
        });

        Float priceSaved = null;
        // Check Currency - If sent currency not default - calculate new price and save it!
        if (createBookingDTO.currency() != null && !defaultCurrency.equals(createBookingDTO.currency())) {
            priceSaved = currencyConverterService.convert(carEntity.getPrice().floatValue(), defaultCurrency, createBookingDTO.currency()).amount() * createBookingDTO.daysToRent();
        }

        // Booking Procedure
        UserEntity currentUser = userService.getUserEntity(userService.getUserName());
        BookingEntity entity = bookingMapper.toEntity(
                createBookingDTO, currentUser.getUserId(),
                BigDecimal.valueOf(carEntity.getPrice().floatValue() * createBookingDTO.daysToRent()).setScale(2, RoundingMode.HALF_UP),
                defaultCurrency,
                priceSaved != null ? BigDecimal.valueOf(priceSaved).setScale(2, RoundingMode.HALF_UP) : null,
                createBookingDTO.currency()
        );
        entity = bookingRepository.save(entity);

        return bookingMapper.toCreateBookingResponseDto(entity);
    }

    @Override
    public BookingEntity updateBooking(Long bookingId, UpdateBookingDTO bookingDTO) {
        BookingEntity bookingEntity = getBookingEntity(bookingId);

        checkIfAuthorized(bookingEntity);

        // TODO Maybe call Currency Converter here
        bookingEntity.setBookedFrom(Objects.requireNonNullElse(bookingDTO.bookedFrom(), bookingEntity.getBookedFrom()));
        bookingEntity.setBookedUntil(Objects.requireNonNullElse(bookingDTO.bookedUntil(), bookingEntity.getBookedUntil()));
        if (bookingEntity.getBookedUntil().isBefore(LocalDateTime.now())) {
            bookingEntity.setBookingStatus(BookingStatus.EXPIRED);
        }

        bookingRepository.save(bookingEntity);

        return bookingEntity;
    }

    @Override
    public void expireBooking(Long bookingId) {
        BookingEntity bookingEntity = getBookingEntity(bookingId);

        checkIfAuthorized(bookingEntity);

        bookingEntity.setBookedUntil(LocalDateTime.now());
        bookingEntity.setBookingStatus(BookingStatus.EXPIRED);

        bookingRepository.save(bookingEntity);
    }

    private BookingEntity getBookingEntity(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find booking with id " + bookingId)
        );
    }


    /**
     * Check UserId with UserId of Booking are equal - if not -> Unauthorized
     *
     * @param bookingEntity to check
     */
    private void checkIfAuthorized(BookingEntity bookingEntity) {
        UserEntity userEntity = userService.getUserEntity(userService.getUserName());
        if (!bookingEntity.getUserId().equals(userEntity.getUserId())) {
            log.error("User {} is not authorized to change the booking with id {}", userEntity.getUserName(), bookingEntity.getBookingId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to update this booking!");
        }
    }
}
