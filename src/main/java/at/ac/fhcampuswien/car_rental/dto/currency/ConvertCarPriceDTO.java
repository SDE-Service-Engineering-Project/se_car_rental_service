package at.ac.fhcampuswien.car_rental.dto.currency;

public record ConvertCarPriceDTO(
        Long carId,
        String toCurrency
) {
}
