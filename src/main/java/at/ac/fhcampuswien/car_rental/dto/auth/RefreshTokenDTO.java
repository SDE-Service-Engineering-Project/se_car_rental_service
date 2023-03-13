package at.ac.fhcampuswien.car_rental.dto.auth;

public record RefreshTokenDTO(
        String refreshToken,
        String userName
) {
}
