package at.ac.fhcampuswien.car_rental.dto.booking;

import java.time.LocalDateTime;

public record UpdateBookingDTO(
        LocalDateTime bookedFrom,
        LocalDateTime bookedUntil
) {
}
