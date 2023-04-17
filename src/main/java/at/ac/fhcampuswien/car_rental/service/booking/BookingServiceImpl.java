package at.ac.fhcampuswien.car_rental.service.booking;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingResponseDTO;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.mapper.BookingMapper;
import at.ac.fhcampuswien.car_rental.mapper.CarMapper;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.service.car.CarService;
import at.ac.fhcampuswien.car_rental.service.currency_converter.CurrencyConverterService;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import at.ac.fhcampuswien.car_rental.utils.LocalDateUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingServiceImpl implements BookingService {
    @Value("${currency-converter.default-currency}")
    String defaultCurrency;
    final BookingRepository bookingRepository;
    final CarService carService;
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
        CarDTO carDTO = carService.getCarById(createBookingDTO.carId());

        assertCarIsNotBooked(createBookingDTO);

        // Booking Procedure
        BookingEntity entity = bookingMapper.toEntity(
                createBookingDTO,
                userService.getUserEntity(userService.getUserName()).getUserId(),
                BigDecimal.valueOf(carDTO.price() * createBookingDTO.daysToRent()).setScale(2, RoundingMode.HALF_UP),
                defaultCurrency,
                calculateSavedPricing(createBookingDTO, carDTO.price()),
                createBookingDTO.currency(),
                createBookingDTO.bookedFrom().isAfter(LocalDateTime.now()) ? BookingStatus.PENDING : BookingStatus.BOOKED
        );

        return bookingMapper.toCreateBookingResponseDto(bookingRepository.save(entity));
    }

    @Override
    public void expireBooking(Long bookingId) {
        BookingEntity bookingEntity = getBookingEntity(bookingId);

        checkIfAuthorized(bookingEntity);

        if (bookingEntity.getBookingStatus().equals(BookingStatus.PENDING)) {
            bookingRepository.delete(bookingEntity);
            return;
        }

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

    private void assertCarIsNotBooked(CreateBookingDTO createBookingDTO) {
        // Check if Car is already booked before proceeding
        List<BookingEntity> savedBookingEntities = bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED);
        if (savedBookingEntities.isEmpty()) return;
        savedBookingEntities
                .stream()
                .filter(item -> !LocalDateUtils.isOverlapping(createBookingDTO.bookedFrom(), createBookingDTO.bookedUntil(), item.getBookedFrom(), item.getBookedUntil()))
                .findAny()
                .orElseThrow(() -> {
                    log.error("Car with the id {} is already booked in that timestamp!", createBookingDTO.carId());
                    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with the id " + createBookingDTO.carId() + " is already booked in the timespan!");
                });
    }

    private BigDecimal calculateSavedPricing(CreateBookingDTO bookingDTO, float price) {
        if (StringUtils.hasText(bookingDTO.currency()) && !bookingDTO.currency().equals(defaultCurrency)) {
            return BigDecimal.valueOf(currencyConverterService.convert(price, defaultCurrency, bookingDTO.currency()).amount() * bookingDTO.daysToRent());
        }

        return null;
    }
}
