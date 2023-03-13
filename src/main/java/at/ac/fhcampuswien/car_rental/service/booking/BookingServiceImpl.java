package at.ac.fhcampuswien.car_rental.service.booking;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.UpdateBookingDTO;
import at.ac.fhcampuswien.car_rental.mapper.BookingMapper;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.service.car.CarService;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;
    CarRepository carRepository;
    BookingMapper bookingMapper;
    UserService userService;

    @Override
    public List<BookingDTO> getMyBookings() {
        UserEntity user = userService.getUserEntity(userService.getUserName());

        return bookingRepository.findAllByUserIdEquals(user.getUserId())
                .stream().map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        // TODO: Throw error if searching for Booking which does not belong to User?
        BookingEntity bookingEntity = getBookingEntity(bookingId);
        return bookingMapper.toDto(bookingEntity);
    }

    @Override
    @Transactional
    public BookingDTO createBooking(CreateBookingDTO createBookingDTO) {
        // Check if Car is already booked before proceeding
        Optional<BookingEntity> savedBookingEntities = bookingRepository.findFirstByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED);
        if (savedBookingEntities.isPresent()) {
            log.error("Car with the id {} is already booked!", createBookingDTO.carId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with the id " + createBookingDTO.carId() + " is already booked!");
        }

        // Check if Car with the Id exists
        carRepository.findById(createBookingDTO.carId()).orElseThrow(() -> {
                    log.error("Could not find car with id {}, could not perform booking!", createBookingDTO.carId());
                    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find car with id " + createBookingDTO.carId());
                }
        );

        UserEntity currentUser = userService.getUserEntity(userService.getUserName());
        BookingEntity entity = bookingMapper.toEntity(createBookingDTO, currentUser.getUserId(), createBookingDTO.carId());
        bookingRepository.save(entity);

        return bookingMapper.toDto(entity);
    }

    @Override
    public BookingDTO updateBooking(Long bookingId, UpdateBookingDTO bookingDTO) {
        BookingEntity bookingEntity = getBookingEntity(bookingId);

        checkIfAuthorized(bookingEntity);

        // TODO Maybe call Currency Converter here
        bookingMapper.updateEntity(bookingEntity, bookingDTO);
        if (bookingEntity.getBookedUntil().isBefore(LocalDateTime.now())) {
            bookingEntity.setBookingStatus(BookingStatus.EXPIRED);
        }

        bookingRepository.save(bookingEntity);

        return bookingMapper.toDto(bookingEntity);
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
     * @param bookingEntity to check
     */
    private void checkIfAuthorized(BookingEntity bookingEntity) {
        UserEntity userEntity = userService.getUserEntity(userService.getUserName());
        if(!bookingEntity.getUserId().equals(userEntity.getUserId())) {
            log.error("User {} is not authorized to change the booking with id {}", userEntity.getUserName(), bookingEntity.getBookingId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not authorized to update this booking!");
        }
    }
}
