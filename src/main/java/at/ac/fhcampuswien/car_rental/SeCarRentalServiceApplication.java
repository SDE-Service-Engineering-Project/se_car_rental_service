package at.ac.fhcampuswien.car_rental;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Car Rental Service", version = "1.0"))
public class SeCarRentalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeCarRentalServiceApplication.class, args);
    }

}
