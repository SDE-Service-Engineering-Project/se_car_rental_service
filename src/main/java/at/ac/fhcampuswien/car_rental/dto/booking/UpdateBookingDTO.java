package at.ac.fhcampuswien.car_rental.dto.booking;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record UpdateBookingDTO(
        LocalDateTime bookedUntil,
        BigInteger price,
        Integer precision,
        String currency
) {
}
