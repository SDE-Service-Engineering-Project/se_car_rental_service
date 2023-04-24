package at.ac.fhcampuswien.car_rental.scheduler;

import at.ac.fhcampuswien.car_rental.AbstractIT;
import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.repository.auth.UserRepository;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingSchedulerTest extends AbstractIT {

    @Autowired
    BookingScheduler bookingScheduler;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    BookingRepository bookingRepository;

    @BeforeEach
    void init() {
        insertTestData();
    }

    @Test
    void should_expire_open_bookings() {
        bookingScheduler.expireBookings();

        Optional<BookingEntity> expired = bookingRepository.findById(1L);

        Assertions.assertTrue(expired.isPresent());
        Assertions.assertEquals(BookingStatus.EXPIRED, expired.get().getBookingStatus());
        Assertions.assertEquals(2, expired.get().getVersion());

        Optional<BookingEntity> booked = bookingRepository.findById(2L);

        Assertions.assertTrue(booked.isPresent());
        Assertions.assertEquals(BookingStatus.BOOKED, booked.get().getBookingStatus());
        Assertions.assertEquals(1, booked.get().getVersion());
    }

    private void insertTestData() {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(4L);
        userEntity.setUserName("test");
        userEntity.setFirstName("test");
        userEntity.setLastName("test");
        userEntity.setPassword("empty");
        userEntity.setCreatedOn(LocalDateTime.now());

        userRepository.save(userEntity);

        CarEntity car = new CarEntity();

        car.setCreatedOn(LocalDateTime.now());
        car.setCarId(1L);
        car.setBrand("BRAND");
        car.setModel("MODEL");
        car.setConstructionYear(2333);
        car.setPrice(BigDecimal.valueOf(122F));
        car.setCurrency("USD");

        carRepository.save(car);

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookingId(1L);
        bookingEntity.setVersion(1);
        bookingEntity.setCreatedOn(LocalDateTime.now());
        bookingEntity.setPrice(BigDecimal.valueOf(123F));
        bookingEntity.setCurrency("USD");
        bookingEntity.setCarId(1L);
        bookingEntity.setUserId(4L);
        bookingEntity.setBookedFrom(Utils.getLocalDate(1, 1, 2022));
        bookingEntity.setBookedUntil(Utils.getLocalDate(1, 2, 2022));
        bookingEntity.setBookingStatus(BookingStatus.BOOKED);

        BookingEntity bookingEntity2 = new BookingEntity();

        bookingEntity2.setBookingId(2L);
        bookingEntity2.setVersion(1);
        bookingEntity2.setCreatedOn(LocalDateTime.now());
        bookingEntity2.setPrice(BigDecimal.valueOf(123F));
        bookingEntity2.setCurrency("USD");
        bookingEntity2.setCarId(1L);
        bookingEntity2.setUserId(4L);
        bookingEntity2.setBookedFrom(Utils.getLocalDate(1, 1, 2022));
        bookingEntity2.setBookedUntil(Utils.getLocalDate(1, 2, 2122));
        bookingEntity2.setBookingStatus(BookingStatus.BOOKED);

        bookingRepository.saveAll(Arrays.asList(bookingEntity, bookingEntity2));
    }

}
