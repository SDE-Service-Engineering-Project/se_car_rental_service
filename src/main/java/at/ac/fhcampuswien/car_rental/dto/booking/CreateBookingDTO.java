package at.ac.fhcampuswien.car_rental.dto.booking;

import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record CreateBookingDTO(
        @NotEmpty(message = "No end date for booking set!")
        LocalDateTime bookedUntil,
        @NotEmpty(message = "No price provided!")
        Float price,
        @NotEmpty(message = "No currency provided!")
        String currency,
        @NotEmpty(message = "No car id provided!")
        Long carId
) {
}
