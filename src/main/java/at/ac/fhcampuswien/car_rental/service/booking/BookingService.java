package at.ac.fhcampuswien.car_rental.service.booking;

import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingResponseDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getMyBookings();
    BookingDTO getBookingById(Long bookingId);
    CreateBookingResponseDTO createBooking(CreateBookingDTO createBookingDTO);
    void expireBooking(Long bookingId);
}
