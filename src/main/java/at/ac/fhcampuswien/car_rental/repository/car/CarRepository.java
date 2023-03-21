package at.ac.fhcampuswien.car_rental.repository.car;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByCarIdNotIn(Collection<Long> carId);
}
