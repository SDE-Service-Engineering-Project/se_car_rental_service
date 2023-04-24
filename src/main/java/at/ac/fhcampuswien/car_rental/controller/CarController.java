package at.ac.fhcampuswien.car_rental.controller;

import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.service.car.CarService;
import at.ac.fhcampuswien.car_rental.utils.LocalDateUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> getAvailableCars(@RequestParam Long neededFrom, @RequestParam Long neededTo) {
        LocalDate neededFromDate = LocalDateUtils.convertLongToLocalDate(neededFrom);
        LocalDate neededToDate = LocalDateUtils.convertLongToLocalDate(neededTo);
        LocalDateUtils.validateTimespan(neededFromDate, neededToDate);
        return ResponseEntity.ok(carService.getAvailableCars(neededFromDate, neededToDate));
    }
}
