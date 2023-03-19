package at.ac.fhcampuswien.car_rental.service.currency_converter;

import at.ac.fhcampuswien.car_rental.config.SoapAuthHandler;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCurrencyDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;
import at.ac.fhcampuswien.car_rental.soap.client.CurrencyConversionService;
import at.ac.fhcampuswien.car_rental.soap.client.CurrencyConversionService_Service;
import at.ac.fhcampuswien.car_rental.soap.client.StringArray;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.handler.Handler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    CurrencyConversionService currencyConversionService;

    @Override
    public ConvertResultDTO convert(ConvertCurrencyDTO convertCurrencyDTO) {
        try {
            Float result = currencyConversionService.convert(convertCurrencyDTO.amount(), convertCurrencyDTO.fromCurrency(), convertCurrencyDTO.toCurrency());
            log.info("Converted {} {} to {} {} via SOAP", convertCurrencyDTO.amount(), convertCurrencyDTO.fromCurrency(), result, convertCurrencyDTO.toCurrency());

            return new ConvertResultDTO(result, convertCurrencyDTO.toCurrency());
        } catch (ServerSOAPFaultException exception) {
            if(exception.getMessage().contains("is not supported")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided Currencies not supported");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not process conversion");
        }
    }

    @Override
    public CurrencyDTO getAllCurrencies() {
        return new CurrencyDTO(currencyConversionService.listCurrencies().getString());
    }
}
