package at.ac.fhcampuswien.car_rental.service.currency_converter;

import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCarPriceDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;

public interface CurrencyConverterService {
    CurrencyDTO getAllCurrencies();
    ConvertResultDTO convert(Float amount, String fromCurrency, String toCurrency);
    ConvertResultDTO convertCarPrice(ConvertCarPriceDTO convertCarPriceDTO);
}
