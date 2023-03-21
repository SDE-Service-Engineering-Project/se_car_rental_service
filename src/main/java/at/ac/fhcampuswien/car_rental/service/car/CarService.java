package at.ac.fhcampuswien.car_rental.service.car;

import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long carId);
    List<CarDTO> getAvailableCars(LocalDateTime neededFrom, LocalDateTime neededTo);
}
