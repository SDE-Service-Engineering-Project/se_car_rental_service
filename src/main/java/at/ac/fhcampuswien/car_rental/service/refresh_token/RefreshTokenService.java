package at.ac.fhcampuswien.car_rental.service.refresh_token;

import at.ac.fhcampuswien.car_rental.dao.auth.RefreshTokenEntity;

public interface RefreshTokenService {
    RefreshTokenEntity generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
