package at.ac.fhcampuswien.car_rental.controller;

import at.ac.fhcampuswien.car_rental.dto.auth.*;
import at.ac.fhcampuswien.car_rental.service.refresh_token.RefreshTokenService;
import at.ac.fhcampuswien.car_rental.service.user.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@OpenAPIDefinition(info = @Info(title = "Authentication Endpoint", version = "1.0"))
public class AuthController {

    UserService userService;
    RefreshTokenService refreshTokenService;

    @Operation(summary = "Sign up a user")
    @PostMapping("/auth/signup")
    public ResponseEntity<Void> registration(@Valid @RequestBody RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Login a user")
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthenticationDTO login = userService.login(loginDTO);
        if (login == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password are wrong");
        }
        return ResponseEntity.ok(login);
    }

    @Operation(summary = "Change a user's password")
    @PutMapping("/user")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody ChangePasswordDTO passwordDTO) {
        userService.changePassword(passwordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Refresh access token")
    @PostMapping("/auth/refresh/token")
    public ResponseEntity<AuthenticationDTO> refreshTokens(@Valid @RequestBody RefreshTokenDTO refreshTokenRequest) {
        return ResponseEntity.ok(userService.refreshAccessToken(refreshTokenRequest));
    }

    /**
     * Since we are using JWT and not opaque tokens a real logout is not possible. (or at lest overkill)
     * Instead, the access token is very short-lived, and when the user logs out we delete the refresh token.
     */
    @Operation(summary = "Logout a user")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutDTO refreshToken) {
        refreshTokenService.deleteRefreshToken(refreshToken.refreshToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete my user account")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMyAccount() {
        userService.deleteMyAccount();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Verify my password")
    @PostMapping("/verify/password")
    public ResponseEntity<Void> verifyPassword(@RequestBody String password) {
        userService.verifyPassword(password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
