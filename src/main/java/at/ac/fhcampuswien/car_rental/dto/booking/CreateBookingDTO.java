package at.ac.fhcampuswien.car_rental.dto.booking;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateBookingDTO(
        LocalDateTime bookedFrom,
        @NotNull(message = "No end date for booking set!")
        LocalDateTime bookedUntil,
        @NotNull(message = "No price provided!")
        Float price,
        @NotEmpty(message = "No currency provided!")
        String currency,
        @NotNull(message = "No car id provided!")
        Long carId
) {
}
