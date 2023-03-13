package at.ac.fhcampuswien.car_rental.controller;

import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.service.car.CarService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Log4j2
public class CarController {
    CarService carService;

    @Operation(summary = "Get all Cars listed")
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @Operation(summary = "Get a Car by Id")
    @GetMapping("/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCarById(carId));
    }
}
