package at.ac.fhcampuswien.car_rental.dto.car;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;

public record CarDTO(
        Long carId,
        LocalDateTime createdOn,
        String brand,
        String model,
        Integer constructionYear,
        BigInteger price,
        Integer precision,
        String currency) {
}
