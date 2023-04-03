package at.ac.fhcampuswien.car_rental.service.booking;

import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingResponseDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.UpdateBookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getMyBookings();
    BookingDTO getBookingById(Long bookingId);
    CreateBookingResponseDTO createBooking(CreateBookingDTO createBookingDTO);
    BookingEntity updateBooking(Long bookingId, UpdateBookingDTO bookingDTO);
    void expireBooking(Long bookingId);
}
