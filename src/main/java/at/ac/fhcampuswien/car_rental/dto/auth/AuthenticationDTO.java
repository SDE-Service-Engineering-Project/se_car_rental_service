package at.ac.fhcampuswien.car_rental.dto.auth;

import java.time.LocalDateTime;

public record AuthenticationDTO(String authToken,
                                String refreshToken,
                                LocalDateTime expiresAt,
                                String userName) {
}

