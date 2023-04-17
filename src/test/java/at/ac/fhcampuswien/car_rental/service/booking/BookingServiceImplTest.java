package at.ac.fhcampuswien.car_rental.service.booking;


import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.mapper.BookingMapper;
import at.ac.fhcampuswien.car_rental.mapper.CarMapper;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.service.car.CarService;
import at.ac.fhcampuswien.car_rental.service.currency_converter.CurrencyConverterService;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import at.ac.fhcampuswien.car_rental.utils.Utils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingServiceImplTest {

    @Mock
    BookingRepository bookingRepository;
    @Mock
    CarService carService;
    @Mock
    CarMapper carMapper;
    @Mock
    BookingMapper bookingMapper;
    @Mock
    UserService userService;
    @Mock
    CurrencyConverterService currencyConverterService;
    @InjectMocks
    BookingServiceImpl bookingService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(bookingService, "defaultCurrency", "USD");
    }

    @Test
    void should_create_booking() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carService.getCarById(createBookingDTO.carId()))
                .thenReturn(Utils.carDTO());
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.bookingEntity()));
        Mockito.when(userService.getUserEntity(Mockito.any())).thenReturn(Utils.userEntity());

        // Act
        bookingService.createBooking(createBookingDTO);

        // Assert
        Mockito.verify(bookingRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void should_not_create_booking_because_wrong_carid() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carService.getCarById(createBookingDTO.carId()))
                .thenThrow(ResponseStatusException.class);

        // Act
        Assertions.assertThrows(
                ResponseStatusException.class, () -> bookingService.createBooking(createBookingDTO)
        );
    }

    @Test
    void should_not_create_booking_because_overlap() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carService.getCarById(createBookingDTO.carId()))
                .thenReturn(Utils.carDTO());
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.conflictingBookingEntity()));

        Assertions.assertThrows(
                ResponseStatusException.class, () -> bookingService.createBooking(createBookingDTO)
        );
    }

    @Test
    void should_not_create_booking_because_overlap_2() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTO();

        Mockito.when(carService.getCarById(createBookingDTO.carId()))
                .thenReturn(Utils.carDTO());
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.conflictingBookingEntity2()));

        Assertions.assertThrows(
                ResponseStatusException.class, () -> bookingService.createBooking(createBookingDTO)
        );
    }

    @Test
    void should_get_booking_by_id() {
        BookingEntity bookingEntity = Utils.bookingEntityWithCar();
        CarDTO carDTO = Utils.carDTO();
        BookingDTO bookingDTO = Utils.bookingDTOFromEntityWithCar();

        Mockito.when(bookingRepository.findById(bookingEntity.getBookingId()))
                .thenReturn(Optional.of(bookingEntity));
        Mockito.when(carMapper.toDto(bookingEntity.getCar()))
                .thenReturn(carDTO);
        Mockito.when(bookingMapper.toDto(bookingEntity, carDTO))
                .thenReturn(bookingDTO);

        BookingDTO result = bookingService.getBookingById(bookingEntity.getBookingId());

        Assertions.assertEquals(bookingEntity.getBookingId(), result.bookingId());
        Mockito.verify(bookingRepository, Mockito.times(1)).findById(bookingEntity.getBookingId());
    }

    @Test
    void should_throw_error_on_wrong_booking_fetch() {
        Long bookingId = 2L;
        Mockito.when(bookingRepository.findById(bookingId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResponseStatusException.class, () -> bookingService.getBookingById(bookingId)
        );
    }

    @Test
    void should_get_booking_by_user() {
        UserEntity userEntity = Utils.userEntity();
        BookingEntity bookingEntity = Utils.bookingEntityWithCar();
        CarDTO carDTO = Utils.carDTO();
        BookingDTO bookingDTO = Utils.bookingDTOFromEntityWithCar();

        Mockito.when(userService.getUserEntity(Mockito.any())).thenReturn(userEntity);
        Mockito.when(bookingRepository.findAllByUserIdEquals(userEntity.getUserId())).thenReturn(List.of(bookingEntity));
        Mockito.when(carMapper.toDto(bookingEntity.getCar()))
                .thenReturn(carDTO);
        Mockito.when(bookingMapper.toDto(bookingEntity, carDTO))
                .thenReturn(bookingDTO);

        List<BookingDTO> bookingDTOs = bookingService.getMyBookings();

        Assertions.assertEquals(1, bookingDTOs.size());
        Assertions.assertEquals(bookingEntity.getBookingId(), bookingDTOs.get(0).bookingId());
    }
    @Test
    void should_expire_booking_with_no_error() {
        UserEntity userEntity = Utils.userEntity();
        BookingEntity bookingEntity = Utils.bookingEntity();
        bookingEntity.setUserId(userEntity.getUserId());

        Mockito.when(bookingRepository.findById(bookingEntity.getBookingId())).thenReturn(Optional.of(bookingEntity));
        Mockito.when(userService.getUserEntity(Mockito.any())).thenReturn(userEntity);

        bookingService.expireBooking(bookingEntity.getBookingId());
    }

    @Test
    void should_save_chosen_currency_to_booking() {
        CreateBookingDTO createBookingDTO = Utils.createBookingDTOWithCurrency();

        CarEntity carEntity = Utils.carEntity();
        Mockito.when(carService.getCarById(createBookingDTO.carId()))
                .thenReturn(Utils.carDTO());
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.bookingEntity()));
        Mockito.when(currencyConverterService.convert(carEntity.getPrice().floatValue(), "USD", createBookingDTO.currency()))
                .thenReturn(new ConvertResultDTO(100200.0f, "EUR"));
        Mockito.when(userService.getUserEntity(Mockito.any())).thenReturn(Utils.userEntity());


        bookingService.createBooking(createBookingDTO);

        Mockito.verify(currencyConverterService, Mockito.times(1)).convert(carEntity.getPrice().floatValue(), "USD", createBookingDTO.currency());
    }

    @Test
    void should_create_booking_with_usd() {
        // Arrange
        CreateBookingDTO createBookingDTO = Utils.createBookingDTOWithUsd();

        Mockito.when(carService.getCarById(createBookingDTO.carId()))
                .thenReturn(Utils.carDTO());
        Mockito.when(bookingRepository.findAllByCarIdEqualsAndBookingStatusEquals(createBookingDTO.carId(), BookingStatus.BOOKED))
                .thenReturn(List.of(Utils.bookingEntity()));
        Mockito.when(userService.getUserEntity(Mockito.any())).thenReturn(Utils.userEntity());

        // Act
        bookingService.createBooking(createBookingDTO);

        // Assert
        Mockito.verify(bookingRepository, Mockito.times(1)).save(Mockito.any());
    }
}
