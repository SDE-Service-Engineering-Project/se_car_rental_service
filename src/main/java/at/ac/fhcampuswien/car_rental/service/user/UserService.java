package at.ac.fhcampuswien.car_rental.service.user;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dto.auth.*;
import reactor.core.publisher.Mono;

public interface UserService {

    void registerUser(RegisterDTO registerDTO);

    Mono<AuthenticationDTO> login(LoginDTO loginDTO);

    AuthenticationDTO refreshAccessToken(RefreshTokenDTO refreshTokenRequest);

    void changePassword(ChangePasswordDTO passwordDTO);

    void deleteMyAccount();

    void verifyPassword(String password);
    UserEntity getUserEntity(String userName);
    String getUserName();
}
