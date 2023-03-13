package at.ac.fhcampuswien.car_rental.service.user;

import at.ac.fhcampuswien.car_rental.dto.auth.*;

public interface UserService {

    void registerUser(RegisterDTO registerDTO);

    AuthenticationDTO login(LoginDTO loginDTO);

    AuthenticationDTO refreshAccessToken(RefreshTokenDTO refreshTokenRequest);

    void changePassword(ChangePasswordDTO passwordDTO);

    void deleteMyAccount();

    void verifyPassword(String password);
}
