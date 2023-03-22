package at.ac.fhcampuswien.car_rental.dto.booking;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UpdateBookingDTO(
        @NotNull
        LocalDateTime bookedFrom,
        @NotNull
        LocalDateTime bookedUntil
) {
}
