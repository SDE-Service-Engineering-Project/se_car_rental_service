package at.ac.fhcampuswien.car_rental.controller;


import at.ac.fhcampuswien.car_rental.dto.booking.BookingDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCarPriceDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertCurrencyDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.ConvertResultDTO;
import at.ac.fhcampuswien.car_rental.dto.currency.CurrencyDTO;
import at.ac.fhcampuswien.car_rental.service.currency_converter.CurrencyConverterService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/currency")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CurrencyController {

    CurrencyConverterService currencyConverterService;
    @Operation(summary = "Get all possible Currencies")
    @GetMapping
    public ResponseEntity<CurrencyDTO> getMyBookings() {
        return ResponseEntity.ok(currencyConverterService.getAllCurrencies());
    }

    @Operation(summary = "Convert from one currency to another")
    @GetMapping("/convert")
    public ResponseEntity<ConvertResultDTO> convert(@RequestBody ConvertCurrencyDTO convertCurrencyDTO) {
        return ResponseEntity.ok(currencyConverterService.convert(convertCurrencyDTO));
    }

    @GetMapping("/car")
    public ResponseEntity<ConvertResultDTO> convertCarPrice(@RequestBody ConvertCarPriceDTO convertCarPriceDTO) {
        return ResponseEntity.ok(currencyConverterService.convertCarPrice(convertCarPriceDTO));
    }
}
