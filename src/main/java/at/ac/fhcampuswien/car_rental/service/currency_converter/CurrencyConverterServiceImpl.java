package at.ac.fhcampuswien.car_rental.service.currency_converter;

import at.ac.fhcampuswien.car_rental.dao.car.CarEntity;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCarPriceDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCurrencyDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;
import at.ac.fhcampuswien.car_rental.repository.car.CarRepository;
import at.ac.fhcampuswien.car_rental.soap.client.CurrencyConversionService;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Log4j2
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    CurrencyConversionService currencyConversionService;
    CarRepository carRepository;

    @Override
    public CurrencyDTO getAllCurrencies() {
        return new CurrencyDTO(currencyConversionService.listCurrencies().getString());
    }

    @Override
    public ConvertResultDTO convert(ConvertCurrencyDTO convertCurrencyDTO) {
        try {
            Float result = currencyConversionService.convert(convertCurrencyDTO.amount(), convertCurrencyDTO.fromCurrency(), convertCurrencyDTO.toCurrency());
            log.info("Converted {} {} to {} {} via SOAP", convertCurrencyDTO.amount(), convertCurrencyDTO.fromCurrency(), result, convertCurrencyDTO.toCurrency());

            return new ConvertResultDTO(result, convertCurrencyDTO.toCurrency());
        } catch (ServerSOAPFaultException exception) {
            if (exception.getMessage().contains("is not supported")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided Currencies not supported");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not process conversion");
        }
    }

    @Override
    public ConvertResultDTO convertCarPrice(ConvertCarPriceDTO convertCarPriceDTO) {
        CarEntity foundCarEntity = carRepository.findById(convertCarPriceDTO.carId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find car with id " + convertCarPriceDTO.carId())
        );

        return convert(
                new ConvertCurrencyDTO(foundCarEntity.getPrice(), foundCarEntity.getCurrency(), convertCarPriceDTO.toCurrency())
        );
    }

}
