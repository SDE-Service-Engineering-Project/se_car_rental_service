package at.ac.fhcampuswien.car_rental.dto.booking;

import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookingDTO(
        Long bookingId,
        LocalDateTime createdOn,
        LocalDate bookedFrom,
        LocalDate bookedUntil,
        String bookingStatus,
        Float price,
        String currency,
        Float priceSaved,
        String currencySaved,
        CarDTO car,
        Long userId
) {
}
