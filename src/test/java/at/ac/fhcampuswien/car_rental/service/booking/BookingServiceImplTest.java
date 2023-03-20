package at.ac.fhcampuswien.car_rental.service.booking;


import at.ac.fhcampuswien.car_rental.AbstractIT;
import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.mapper.BookingMapper;
import at.ac.fhcampuswien.car_rental.mapper.CarMapper;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import at.ac.fhcampuswien.car_rental.utils.Utils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingServiceImplTest {

    @Mock
    BookingRepository bookingRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    CarMapper carMapper;
    @Mock
    BookingMapper bookingMapper;
    @Mock
    UserService userService;
    @InjectMocks
    BookingServiceImpl bookingService;

    @Test
    public void should_create_booking() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carRepository.findById(createBookingDTO.carId()))
                .thenReturn(Optional.ofNullable(Utils.carEntity()));
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.bookingEntity()));
        Mockito.when(userService.getUserEntity(Mockito.any())).thenReturn(Utils.userEntity());

        // Act
        bookingService.createBooking(createBookingDTO);

        // Assert
        Mockito.verify(bookingRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void should_not_create_booking_because_overlap() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carRepository.findById(createBookingDTO.carId()))
                .thenReturn(Optional.ofNullable(Utils.carEntity()));
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.conflictingBookingEntity()));

        Assertions.assertThrows(
                ResponseStatusException.class, () -> {
                    bookingService.createBooking(createBookingDTO);
                }
        );
    }

    @Test
    public void should_not_create_booking_because_overlap_2() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carRepository.findById(createBookingDTO.carId()))
                .thenReturn(Optional.ofNullable(Utils.carEntity()));
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.conflictingBookingEntity2()));

        Assertions.assertThrows(
                ResponseStatusException.class, () -> {
                    bookingService.createBooking(createBookingDTO);
                }
        );
    }
}
