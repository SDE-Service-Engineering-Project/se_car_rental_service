package at.ac.fhcampuswien.car_rental.dto.booking;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CreateBookingDTO(
        LocalDateTime bookedFrom,
        @NotNull(message = "No end date for booking set!")
        LocalDateTime bookedUntil,
        @NotNull(message = "No days to rent set!")
        Long daysToRent,
        @NotNull(message = "No car id provided!")
        Long carId,
        String currency
) {
}
