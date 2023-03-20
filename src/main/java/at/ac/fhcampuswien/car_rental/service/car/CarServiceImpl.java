package at.ac.fhcampuswien.car_rental.service.car;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.mapper.CarMapper;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CarServiceImpl implements CarService {
    CarRepository carRepository;
    CarMapper carMapper;

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarById(Long carId) {
        CarEntity foundCarEntity = carRepository.findById(carId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find car with id " + carId)
        );
        return carMapper.toDto(foundCarEntity);
    }
}