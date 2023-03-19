package at.ac.fhcampuswien.car_rental.dto.booking;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record BookingDTO(
        Long bookingId,
        LocalDateTime createdOn,
        LocalDateTime bookedUntil,
        String bookingStatus,
        Float price,
        String currency,
        Long carId,
        Long userId
) {
}
