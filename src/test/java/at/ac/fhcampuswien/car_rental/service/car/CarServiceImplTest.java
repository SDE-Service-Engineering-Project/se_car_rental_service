package at.ac.fhcampuswien.car_rental.service.car;

import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.car.AvailabilityDTO;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
import at.ac.fhcampuswien.car_rental.mapper.CarMapper;
import at.ac.fhcampuswien.car_rental.repository.booking.BookingRepository;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.utils.Utils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarServiceImplTest {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    CarRepository carRepository;
    @Mock
    CarMapper carMapper;
    @InjectMocks
    CarServiceImpl carService;

    @Test
    public void should_show_available_car() {
        List<CarEntity> carEntities = Utils.carEntitiesAsList();
        List<BookingEntity> bookingEntity = Utils.bookingEntitiesAsList();
        AvailabilityDTO availabilityDTO = Utils.availabilityDTO();

        Mockito.when(bookingRepository.findAllByBookingStatusEquals(BookingStatus.BOOKED))
                .thenReturn(bookingEntity);
        Mockito.when(carRepository.findAll()).thenReturn(carEntities);

        List<CarDTO> result = carService.getAvailableCars(availabilityDTO);

        Mockito.verify(carRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void should_not_show_available_car() {
        List<BookingEntity> bookingEntity = Utils.bookingEntitiesAsList();
        AvailabilityDTO availabilityDTO = Utils.availabilityDTOButUnavailable();
        List<Long> notAvailableCarId = List.of(Utils.secondBookingEntity().getCarId());

        Mockito.when(bookingRepository.findAllByBookingStatusEquals(BookingStatus.BOOKED))
                .thenReturn(bookingEntity);
        Mockito.when(carRepository.findByCarIdNotIn(notAvailableCarId))
                .thenReturn(List.of(Utils.carEntity()));

        List<CarDTO> result = carService.getAvailableCars(availabilityDTO);

        Mockito.verify(carRepository, Mockito.times(1)).findByCarIdNotIn(notAvailableCarId);
        Assertions.assertEquals(1, result.size());
    }
}
