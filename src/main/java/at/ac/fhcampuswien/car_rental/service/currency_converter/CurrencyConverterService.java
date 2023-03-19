package at.ac.fhcampuswien.car_rental.service.currency_converter;

import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCurrencyDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;

import java.util.List;

public interface CurrencyConverterService {
    ConvertResultDTO convert(ConvertCurrencyDTO convertCurrencyDTO);
    CurrencyDTO getAllCurrencies();
}
