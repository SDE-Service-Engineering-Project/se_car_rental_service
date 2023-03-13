package at.ac.fhcampuswien.car_rental.mapper;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dto.auth.RegisterDTO;
import at.ac.fhcampuswien.car_rental.mapper.crypto.EncodedMapping;
import at.ac.fhcampuswien.car_rental.mapper.crypto.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class},
        uses = {PasswordEncoderMapper.class})
public interface UserMapper {

    @Mapping(target = "createdOn", expression = "java(LocalDateTime.now())")
    @Mapping(target = "password", source = "password", qualifiedBy = EncodedMapping.class)
    UserEntity toEntity(RegisterDTO dto);
}
