package at.ac.fhcampuswien.car_rental.mapper;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.mapper.crypto.PasswordEncoderMapper;
import org.mapstruct.Mapper;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDto(CarEntity entity);
}
