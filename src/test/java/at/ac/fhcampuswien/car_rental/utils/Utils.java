package at.ac.fhcampuswien.car_rental.utils;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@UtilityClass
public class Utils {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public CarEntity carEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarId(1L);
        carEntity.setPrice(10000.0f);
        carEntity.setCurrency("USD");
        carEntity.setBrand("VW");
        carEntity.setModel("Golf");
        carEntity.setConstructionYear(2018);
        carEntity.setCreatedOn(LocalDateTime.now());
        carEntity.setVersion(0);

        return carEntity;
    }

    public CarEntity secondCarEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarId(2L);
        carEntity.setPrice(25000.0f);
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
        LocalDateTime bookedFrom = LocalDateTime.parse("01-03-2023 13:00", formatter);
        LocalDateTime bookedTo = LocalDateTime.parse("04-03-2023 13:00", formatter);
        Long carId = 1L;
        return new CreateBookingDTO(bookedFrom, bookedTo, bookedFrom.until(bookedTo, ChronoUnit.DAYS), carId);
    }

    public BookingEntity bookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(1L);
        bookingEntity.setPrice(200000.0f);
        bookingEntity.setCurrency("USD");
        LocalDateTime bookedFrom = LocalDateTime.parse("05-03-2023 13:00", formatter);
        LocalDateTime bookedTo = LocalDateTime.parse("07-03-2023 13:00", formatter);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCarId(1L);

        return bookingEntity;
    }

    public BookingEntity secondBookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(2L);
        bookingEntity.setPrice(200000.0f);
        bookingEntity.setCurrency("USD");
        LocalDateTime bookedFrom = LocalDateTime.parse("01-03-2023 13:00", formatter);
        LocalDateTime bookedTo = LocalDateTime.parse("04-03-2023 13:00", formatter);
        bookingEntity.setBookedFrom(bookedFrom);
        bookingEntity.setBookedUntil(bookedTo);
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);
        bookingEntity.setUserId(1L);
        bookingEntity.setCarId(2L);

        return bookingEntity;
    }

    public List<BookingEntity> bookingEntitiesAsList() {
        return List.of(bookingEntity(), secondBookingEntity());
    }

    public BookingEntity conflictingBookingEntity() {
        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(2L);
        bookingEntity.setPrice(200000.0f);
        bookingEntity.setCurrency("USD");
        LocalDateTime bookedFrom = LocalDateTime.parse("03-03-2023 13:00", formatter);
        LocalDateTime bookedTo = LocalDateTime.parse("04-03-2023 13:00", formatter);
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
        bookingEntity.setPrice(200000.0f);
        bookingEntity.setCurrency("USD");
        LocalDateTime bookedFrom = LocalDateTime.parse("03-03-2023 13:00", formatter);
        LocalDateTime bookedTo = LocalDateTime.parse("05-03-2023 13:00", formatter);
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

    public LocalDateTime getLocalDateTime(String day, String month) {
        return LocalDateTime.parse(day + "-" + month + "-2023 00:00", formatter);
    }
}
