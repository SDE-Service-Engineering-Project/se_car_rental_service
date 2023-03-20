package at.ac.fhcampuswien.car_rental.mapper;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.CreateBookingResponseDTO;
import at.ac.fhcampuswien.car_rental.dto.booking.UpdateBookingDTO;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, Objects.class})
public interface BookingMapper {

    @Mapping(target = "bookingId", source = "entity.bookingId")
    @Mapping(target = "bookedFrom", source = "entity.bookedFrom")
    @Mapping(target = "bookedUntil", source = "entity.bookedUntil")
    @Mapping(target = "bookingStatus", source = "entity.bookingStatus")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "currency", source = "entity.currency")
    @Mapping(target = "createdOn", source = "entity.createdOn")
    @Mapping(target = "userId", source = "entity.userId")
    @Mapping(target = "car", source = "carDTO")
    BookingDTO toDto(BookingEntity entity, CarDTO carDTO);

    @Mapping(target = "bookingStatus", constant = "BOOKED")
    @Mapping(target = "createdOn", expression = "java(LocalDateTime.now())")
    @Mapping(target = "bookedFrom", expression = "java(Objects.requireNonNullElse(dto.bookedFrom(), LocalDateTime.now()))")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "price", source = "dto.price")
    @Mapping(target = "currency", source = "dto.currency")
    @Mapping(target = "carId", source = "carId")
    @Mapping(target = "userId", source = "userId")
    BookingEntity toEntity(CreateBookingDTO dto, Long userId, Long carId);

    @Mapping(target = "bookedFrom", expression = "java(Objects.requireNonNullElse(dto.bookedUntil(), entity.getBookedFrom()))")
    @Mapping(target = "bookedUntil", expression = "java(Objects.requireNonNullElse(dto.bookedUntil(), entity.getBookedUntil()))")
    @Mapping(target = "price", expression = "java(Objects.requireNonNullElse(dto.price(), entity.getPrice()))")
    @Mapping(target = "currency", expression = "java(Objects.requireNonNullElse(dto.currency(), entity.getCurrency()))")
    void updateEntity(@MappingTarget BookingEntity entity, UpdateBookingDTO dto);

    CreateBookingResponseDTO toCreateBookingResponseDto(BookingEntity entity);
}
