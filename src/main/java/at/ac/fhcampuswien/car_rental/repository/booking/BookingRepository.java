package at.ac.fhcampuswien.car_rental.repository.booking;

import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findAllByCarIdEqualsAndBookingStatusEquals(Long carId, BookingStatus bookingStatus);
    List<BookingEntity> findAllByUserIdEquals(Long userId);
    List<BookingEntity> findAllByBookingStatusEquals(BookingStatus bookingStatus);
}
