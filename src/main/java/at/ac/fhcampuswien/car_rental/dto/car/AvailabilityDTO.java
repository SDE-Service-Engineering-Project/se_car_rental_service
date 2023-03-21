package at.ac.fhcampuswien.car_rental.dto.car;

import java.time.LocalDateTime;

public record AvailabilityDTO(
        LocalDateTime neededFrom,
        LocalDateTime neededTo
) {

}
