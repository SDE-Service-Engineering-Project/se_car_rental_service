package at.ac.fhcampuswien.car_rental.service.currency_converter;

import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCarPriceDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCurrencyDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;

import java.util.List;

public interface CurrencyConverterService {
    CurrencyDTO getAllCurrencies();
    ConvertResultDTO convert(ConvertCurrencyDTO convertCurrencyDTO);
    ConvertResultDTO convertCarPrice(ConvertCarPriceDTO convertCarPriceDTO);
}
