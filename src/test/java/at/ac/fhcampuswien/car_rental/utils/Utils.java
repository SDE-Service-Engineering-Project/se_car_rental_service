package at.ac.fhcampuswien.car_rental.utils;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class Utils {
    public CarEntity carEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarId(1L);
        carEntity.setPrice(BigDecimal.valueOf(10000.0f));
        carEntity.setCurrency("USD");
        carEntity.setBrand("Toyota");
        carEntity.setModel("Model 1");
        carEntity.setConstructionYear(2018);
        carEntity.setCreatedOn(LocalDateTime.now());
        carEntity.setVersion(0);

        return carEntity;
    }

    public CarDTO carDTO() {
        CarEntity carEntity = carEntity();

        return new CarDTO(carEntity.getCarId(), carEntity.getCreatedOn(), carEntity.getBrand(), carEntity.getModel(), carEntity.getConstructionYear(), carEntity.getPrice().floatValue(), carEntity.getCurrency());
    }

    public CarEntity secondCarEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarId(2L);
        carEntity.setPrice(BigDecimal.valueOf(25000.0f));
        carEntity.setCurrency("USD");
        carEntity.setBrand("Mercedes Benz");
        carEntity.setModel("Whatever");
        carEntity.setConstructionYear(2018);
        carEntity.setCreatedOn(LocalDateTime.now());
        carEntity.setVersion(0);

        return carEntity;
    }

    public List<CarEntity> carEntitiesAsList() {
        return List.of(carEntity(), secondCarEntity());
    }

    public CreateBookingDTO createBookingDTO() {
        LocalDate bookedFrom = getLocalDate(1, 3);
        LocalDate bookedTo = getLocalDate(4, 3);
        Long carId = 1L;
        return new CreateBookingDTO(bookedFrom, bookedTo, carId, null);
    }

    public CreateBookingDTO createBookingDTOWithUsd() {
        LocalDate bookedFrom = getLocalDate(1, 3);
        LocalDate bookedTo = getLocalDate(4, 3);
        Long carId = 1L;
        String currency = "USD";
        return new CreateBookingDTO(bookedFrom, bookedTo, carId, currency);
    }

    public CreateBookingDTO createBookingDTOWithCurrency() {
        LocalDate bookedFrom = getLocalDate(1, 3);
        LocalDate bookedTo = getLocalDate(4, 3);
        Long carId = 1L;
        String currency = "EUR";
        return new CreateBookingDTO(bookedFrom, bookedTo, carId, currency);
    }

    public BookingEntity bookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(1L);
        bookingEntity.setPrice(BigDecimal.valueOf(200000.0f));
        bookingEntity.setCurrency("USD");
        bookingEntity.setPriceSaved(null);
        bookingEntity.setCurrencySaved("USD");
        LocalDate bookedFrom = getLocalDate(5, 3);
        LocalDate bookedTo = getLocalDate(7, 3);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCarId(1L);

        return bookingEntity;
    }


    public BookingEntity pendingBookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(5L);
        bookingEntity.setPrice(BigDecimal.valueOf(200000.0f));
        bookingEntity.setCurrency("USD");
        bookingEntity.setPriceSaved(null);
        bookingEntity.setCurrencySaved("USD");
        LocalDate bookedFrom = LocalDate.now().plusDays(2);
        LocalDate bookedTo = LocalDate.now().plusDays(7);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.PENDING);
        bookingEntity.setUserId(4L);
        bookingEntity.setCarId(1L);
        bookingEntity.setVersion(1);

        return bookingEntity;
    }

    public BookingEntity secondBookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(2L);
        bookingEntity.setPrice(BigDecimal.valueOf(200000.0f));
        bookingEntity.setCurrency("USD");
        LocalDate bookedFrom = getLocalDate(1, 3);
        LocalDate bookedTo = getLocalDate(4, 3);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCarId(2L);

        return bookingEntity;
    }

    public BookingEntity bookingEntityWithCar() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(1L);
        bookingEntity.setPrice(BigDecimal.valueOf(200000.0f));
        bookingEntity.setCurrency("USD");
        LocalDate bookedFrom = getLocalDate(5, 3);
        LocalDate bookedTo = getLocalDate(7, 3);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCar(carEntity());
        bookingEntity.setCarId(bookingEntity.getCar().getCarId());

        return bookingEntity;
    }

    public BookingDTO bookingDTOFromEntityWithCar() {
        BookingEntity bookingEntity = bookingEntity();

        return new BookingDTO(
                bookingEntity.getBookingId(),
                bookingEntity.getCreatedOn(),
                bookingEntity.getBookedFrom(),
                bookingEntity.getBookedUntil(),
                bookingEntity.getBookingStatus().toString(),
                bookingEntity.getPrice().floatValue(),
                bookingEntity.getCurrency(),
                null,
                bookingEntity.getCurrencySaved(),
                carDTO(),
                bookingEntity.getUserId()
        );
    }

    public List<BookingEntity> bookingEntitiesAsList() {
        return List.of(bookingEntity(), secondBookingEntity());
    }

    public BookingEntity conflictingBookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(2L);
        bookingEntity.setPrice(BigDecimal.valueOf(200000.0f));
        bookingEntity.setCurrency("USD");
        LocalDate bookedFrom = getLocalDate(3, 3);
        LocalDate bookedTo = getLocalDate(4, 3);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCarId(1L);

        return bookingEntity;
    }

    public BookingEntity conflictingBookingEntity2() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(2L);
        bookingEntity.setPrice(BigDecimal.valueOf(200000.0f));
        bookingEntity.setCurrency("USD");
        LocalDate bookedFrom = getLocalDate(3, 3);
        LocalDate bookedTo = getLocalDate(5, 3);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCarId(1L);

        return bookingEntity;
    }

    public UserEntity userEntity() {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(1L);
        userEntity.setUserName("user");
        userEntity.setPassword("password");
        userEntity.setFirstName("first");
        userEntity.setLastName("last");
        userEntity.setCreatedOn(LocalDateTime.now());
        userEntity.setVersion(0);

        return userEntity;
    }

    public LocalDate getLocalDate(int day, int month) {
        return LocalDate.of(2023, month, day);
    }
    public LocalDate getLocalDate(int day, int month, int year) {
        return LocalDate.of(year, month, day);
    }
}
