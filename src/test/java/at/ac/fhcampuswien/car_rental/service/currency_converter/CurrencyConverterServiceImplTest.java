package at.ac.fhcampuswien.car_rental.service.currency_converter;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCarPriceDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.soap.client.CurrencyConversionService;
import at.ac.fhcampuswien.car_rental.soap.client.StringArray;
import at.ac.fhcampuswien.car_rental.utils.Utils;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyConverterServiceImplTest {
    @Mock
    CurrencyConversionService currencyConversionService;
    @Mock
    CarRepository carRepository;
    @InjectMocks
    CurrencyConverterServiceImpl currencyConverterService;

    @Test
    void should_convert_currency() {
        Float amount = 200.0f;
        String fromCurrency = "USD";
        String toCurrenty = "EUR";
        Float resultAmount = 210.0f;

        Mockito.when(currencyConversionService.convert(amount, fromCurrency, toCurrenty))
                .thenReturn(resultAmount);

        ConvertResultDTO result = currencyConverterService.convert(amount, fromCurrency, toCurrenty);

        Assertions.assertEquals(resultAmount, result.amount());
        Assertions.assertEquals(toCurrenty, result.currency());

    }

    @Test
    void should_throw_error_if_something_wrong() {
        Float amount = 200.0f;
        String fromCurrency = "USD";
        String toCurrenty = "EU";

        Mockito.when(currencyConversionService.convert(amount, fromCurrency, toCurrenty))
                .thenThrow(ServerSOAPFaultException.class);

        Assertions.assertThrows(
                ResponseStatusException.class, () -> {
                    currencyConverterService.convert(amount, fromCurrency, toCurrenty);
                }
        );
    }

    @Test
    void should_get_all_currencies() {
        List<String> listOfCurrencies = List.of("EUR", "USD", "ZAR");

        StringArray array = Mockito.mock(StringArray.class);
        Mockito.when(currencyConversionService.listCurrencies())
                .thenReturn(array);
        Mockito.when(array.getString()).thenReturn(listOfCurrencies);

        CurrencyDTO result = currencyConverterService.getAllCurrencies();

        Assertions.assertEquals(listOfCurrencies, result.currencies());
    }

    @Test
    void should_convert_car_price() {
        CarEntity carEntity = Utils.carEntity();
        ConvertCarPriceDTO convertCarPriceDTO = new ConvertCarPriceDTO(carEntity.getCarId(), "EUR");
        float expectedResult = carEntity.getPrice() + 10.0f;

        Mockito.when(carRepository.findById(carEntity.getCarId()))
                .thenReturn(Optional.of(carEntity));
        Mockito.when(currencyConversionService.convert(carEntity.getPrice(), carEntity.getCurrency(), convertCarPriceDTO.toCurrency()))
                .thenReturn(expectedResult);

        ConvertResultDTO result = currencyConverterService.convertCarPrice(convertCarPriceDTO);

        Assertions.assertEquals(expectedResult, result.amount());
        Assertions.assertEquals(convertCarPriceDTO.toCurrency(), result.currency());
    }

    @Test
    void should_not_convert_non_existing_car() {
        ConvertCarPriceDTO convertCarPriceDTO = new ConvertCarPriceDTO(2L, "EUR");

        Mockito.when(carRepository.findById(convertCarPriceDTO.carId()))
                .thenReturn(Optional.empty());


        Assertions.assertThrows(
                ResponseStatusException.class, () -> {
                    currencyConverterService.convertCarPrice(convertCarPriceDTO);
                }
        );
    }
}
