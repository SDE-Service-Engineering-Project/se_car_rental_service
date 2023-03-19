package at.ac.fhcampuswien.car_rental.dto.car;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record CarDTO(
        Long carId,
        LocalDateTime createdOn,
        String brand,
        String model,
        Integer constructionYear,
        Float price,
        String currency) {
}
