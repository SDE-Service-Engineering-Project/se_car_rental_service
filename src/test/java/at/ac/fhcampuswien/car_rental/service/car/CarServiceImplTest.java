package at.ac.fhcampuswien.car_rental.service.car;

import at.ac.fhcampuswien.car_rental.dao.booking.BookingEntity;
import at.ac.fhcampuswien.car_rental.dao.booking.BookingStatus;
import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.car.CarDTO;
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

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarServiceImplTest {
    @Mock
    BookingRepository bookingRepository;
    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarServiceImpl carService;

    @Test
    public void should_show_available_car() {
        List<CarEntity> carEntities = Utils.carEntitiesAsList();
        List<BookingEntity> bookingEntity = Utils.bookingEntitiesAsList();
        LocalDateTime bookedFrom = Utils.getLocalDateTime("08", "03");
        LocalDateTime bookedTo = Utils.getLocalDateTime("10", "03");

        Mockito.when(bookingRepository.findAllByBookingStatusEquals(BookingStatus.BOOKED))
                .thenReturn(bookingEntity);
        Mockito.when(carRepository.findAll()).thenReturn(carEntities);

        List<CarDTO> result = carService.getAvailableCars(bookedFrom, bookedTo);

        Mockito.verify(carRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(carEntities.size(), result.size());
    }

    @Test
    public void should_not_show_available_car() {
        List<BookingEntity> bookingEntity = Utils.bookingEntitiesAsList();
        List<Long> notAvailableCarId = List.of(Utils.secondBookingEntity().getCarId());
        LocalDateTime bookedFrom = Utils.getLocalDateTime("28", "02");
        LocalDateTime bookedTo = Utils.getLocalDateTime("03", "03");
        Mockito.when(bookingRepository.findAllByBookingStatusEquals(BookingStatus.BOOKED))
                .thenReturn(bookingEntity);
        Mockito.when(carRepository.findByCarIdNotIn(notAvailableCarId))
                .thenReturn(List.of(Utils.carEntity()));

        List<CarDTO> result = carService.getAvailableCars(bookedFrom, bookedTo);

        Mockito.verify(carRepository, Mockito.times(1)).findByCarIdNotIn(notAvailableCarId);
        Assertions.assertEquals(1, result.size());
    }
}
