package at.ac.fhcampuswien.car_rental.dto.booking;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record UpdateBookingDTO(
        LocalDateTime bookedFrom,
        LocalDateTime bookedUntil,
        Float price,
        String currency
) {
}
