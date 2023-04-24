package at.ac.fhcampuswien.car_rental.dto.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateBookingResponseDTO(
        Long bookingId,
        LocalDateTime createdOn,
        LocalDate bookedFrom,
        LocalDate bookedUntil,
        String bookingStatus,
        Float price,
        String currency,
        Float priceSaved,
        String currencySaved,
        Long carId,
        Long userId
) {
}
