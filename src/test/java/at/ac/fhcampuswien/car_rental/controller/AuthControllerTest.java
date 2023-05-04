package at.ac.fhcampuswien.car_rental.controller;

import at.ac.fhcampuswien.car_rental.AbstractIT;
import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dto.auth.AuthenticationDTO;
import at.ac.fhcampuswien.car_rental.dto.auth.ChangePasswordDTO;
import at.ac.fhcampuswien.car_rental.dto.auth.LoginDTO;
import at.ac.fhcampuswien.car_rental.dto.auth.RegisterDTO;
import at.ac.fhcampuswien.car_rental.repository.auth.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
class AuthControllerTest extends AbstractIT {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @AfterEach
    void remove() {
        userRepository.deleteAll();
    }

    @Test
    void should_create_user() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("testUser", "firstName", "lastName", "auto2413");

        webTestClient.post()
                .uri("/api/v1/auth/signup")
                .header("content-type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ObjectMapper().writeValueAsString(registerDTO)))
                .exchange()
                .expectStatus().isCreated()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();

        Optional<UserEntity> testEntity = userRepository.findByUserName("testUser");

        assertTrue(testEntity.isPresent());
        Assertions.assertNotNull(testEntity.get());
        Assertions.assertEquals("testUser", testEntity.get().getUserName());
    }

    @Test
    void user_already_exists_at_registration() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("testinger", "test", "test", "auto2413");

        webTestClient.post()
                .uri("/api/v1/auth/signup")
                .header("content-type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ObjectMapper().writeValueAsString(registerDTO)))
                .exchange()
                .expectStatus().isCreated()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();

        webTestClient.post()
                .uri("/api/v1/auth/signup")
                .header("content-type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ObjectMapper().writeValueAsString(registerDTO)))
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();


        Optional<UserEntity> testEntity = userRepository.findByUserName("testinger");

        assertTrue(testEntity.isPresent());
        Assertions.assertNotNull(testEntity.get());
    }

    @Test
    void register_and_login() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("testUser", "firstName", "lastName", "auto2413");

        webTestClient.post()
                .uri("/api/v1/auth/signup")
                .header("content-type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ObjectMapper().writeValueAsString(registerDTO)))
                .exchange()
                .expectStatus().isCreated()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();


        LoginDTO loginDTO = new LoginDTO("testUser", "auto2413");

        AuthenticationDTO authenticationDTO = webTestClient.post()
                .uri("/api/v1/auth/login")
                .header("content-type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ObjectMapper().writeValueAsString(loginDTO)))
                .exchange()
                .expectStatus().isOk()
                .returnResult(AuthenticationDTO.class)
                .getResponseBody()
                .blockFirst();

        assertThat(authenticationDTO).isNotNull();
        assertThat(authenticationDTO.userName()).isEqualTo("testUser");
    }

    @Test
    @WithMockUser(username = "testUser")
    void password_change_mismatch() throws Exception {
        // Arrange
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("auto2413", "auto1234", "auto12345");

        webTestClient.put()
                .uri("/api/v1/user")
                .header("content-type", "application/json")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new ObjectMapper().writeValueAsString(changePasswordDTO)))
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();
    }
}
