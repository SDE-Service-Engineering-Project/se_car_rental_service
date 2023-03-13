package at.ac.fhcampuswien.car_rental.repository.car;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
