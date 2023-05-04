package at.ac.fhcampuswien.car_rental.dto.booking;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateBookingDTO(
        LocalDate bookedFrom,
        @NotNull(message = "No end date for booking set!")
        LocalDate bookedUntil,
        @NotNull(message = "No car id provided!")
        Long carId,
        String currency
) {
}
