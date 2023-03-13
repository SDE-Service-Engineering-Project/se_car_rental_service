package at.ac.fhcampuswien.car_rental.repository.booking;

import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    Optional<BookingEntity> findFirstByCarIdEqualsAndBookingStatusEquals(Long carId, BookingStatus bookingStatus);
    List<BookingEntity> findAllByUserIdEquals(Long userId);
}
