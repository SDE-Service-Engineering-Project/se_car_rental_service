package at.ac.fhcampuswien.car_rental.dto.booking;

import java.time.LocalDateTime;

public record CreateBookingResponseDTO(
        Long bookingId,
        LocalDateTime createdOn,
        LocalDateTime bookedFrom,
        LocalDateTime bookedUntil,
        String bookingStatus,
        Float price,
        String currency,
        Float priceSaved,
        String currencySaved,
        Long carId,
        Long userId
) {
}
