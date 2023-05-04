package at.ac.fhcampuswien.car_rental.service.user;

import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dto.auth.*;
import at.ac.fhcampuswien.car_rental.mapper.UserMapper;
import at.ac.fhcampuswien.car_rental.mapper.crypto.PasswordEncoderMapper;
import at.ac.fhcampuswien.car_rental.repository.auth.UserRepository;
import at.ac.fhcampuswien.car_rental.service.refresh_token.RefreshTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository repository;
    UserMapper userMapper;
    ReactiveAuthenticationManager authenticationManager;
    JwtProvider jwtProvider;
    PasswordEncoderMapper passwordEncoderMapper;
    RefreshTokenService refreshTokenService;


    @Override
    @Transactional
    public void registerUser(RegisterDTO registerDTO) {
        UserEntity userEntity = userMapper.toEntity(registerDTO);
        try {
            repository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            log.error("Username {} already exists", registerDTO.userName());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordDTO passwordDTO) {
        if (!passwordDTO.newPassword().equals(passwordDTO.verifiedPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password mismatch");
        }
        UserEntity user = getUserEntity(getUserName());
        validatePassword(passwordDTO.currentPassword(), user.getPassword());

        user.setPassword(passwordEncoderMapper.encodePlainText(passwordDTO.newPassword()));
    }

    @Override
    @Transactional
    public void deleteMyAccount() {
        String userName = getUserName();
        log.info("deleting account {}", userName);
        repository.deleteByUserName(userName);
    }

    @Override
    public void verifyPassword(String password) {
        validatePassword(password, getUserEntity(getUserName()).getPassword());
    }

    @Override
    public UserEntity getUserEntity(String userName) {
        return repository.findByUserName(userName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User cannot be found"));
    }

    /**
     * Authenticates a user based on username and password in spring security context
     */
    @Override
    @Transactional
    public Mono<AuthenticationDTO> login(LoginDTO loginDTO) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password()))
                    .map(auth -> {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        return jwtProvider.generateToken(auth);
                    })
                    .map(x -> new AuthenticationDTO(x, refreshTokenService.generateRefreshToken().getToken(), LocalDateTime.now().plusSeconds(jwtProvider.getJwtExpirationInSeconds()), loginDTO.userName()));
        } catch (Exception e) {
            log.error("Could not find user name {} or password wrong!", loginDTO.userName());
        }

        return Mono.empty();

    }

    @Override
    public AuthenticationDTO refreshAccessToken(RefreshTokenDTO refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.refreshToken());
        return new AuthenticationDTO(jwtProvider.generateTokenWithUserName(refreshTokenRequest.userName()), refreshTokenService.generateRefreshToken().getToken(), LocalDateTime.now().plusSeconds(jwtProvider.getJwtExpirationInSeconds()), refreshTokenRequest.userName());
    }

    @Override
    public String getUserName() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getSubject();
    }

    private void validatePassword(String password, String dbPassword) {
        if (!passwordEncoderMapper.doesPasswordMatch(password, dbPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password wrong");
        }
    }
}
