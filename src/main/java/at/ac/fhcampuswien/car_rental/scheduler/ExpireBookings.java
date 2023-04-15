package at.ac.fhcampuswien.car_rental.scheduler;

import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExpireBookings {

    BookingRepository bookingRepository;

    // this is a quick and dirty fix
    @Scheduled(cron = "0 0/15 * * * ?") // every fifteen minutes
    @Transactional
    public void expireBookings() {
        log.info("looking for bookings to expire");
        List<BookingEntity> list = bookingRepository.findAllByBookingStatusEquals(BookingStatus.BOOKED)
                .stream()
                .filter((bookingEntity -> !bookingEntity.getBookedUntil().isAfter(LocalDateTime.now())))
                .map(BookingEntity::setExpired)
                .toList();

        log.info("found {} bookings to expire", list.size());

        bookingRepository.saveAll(list);
    }
}
