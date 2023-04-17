package at.ac.fhcampuswien.car_rental.controller;

import at.ac.fhcampuswien.car_rental.AbstractIT;
import at.ac.fhcampuswien.car_rental.dao.auth.UserEntity;
import at.ac.fhcampuswien.car_rental.dto.auth.ChangePasswordDTO;
import at.ac.fhcampuswien.car_rental.dto.auth.RegisterDTO;
import at.ac.fhcampuswien.car_rental.repository.auth.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class AuthControllerTest extends AbstractIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void should_create_user() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("testUser", "firstName", "lastName", "auto2413");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        UserEntity testEntity = userRepository.findByUserName("testUser").get();

        Assertions.assertNotNull(testEntity);
        Assertions.assertEquals(testEntity.getUserName(), "testUser");
    }

    @Test
    void user_already_exists_at_registration() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO("testinger", "test", "test", "auto2413");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String error = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/auth/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(registerDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();

        UserEntity testingerEntity = userRepository.findByUserName("testinger").get();

        Assertions.assertNotNull(testingerEntity);
        Assertions.assertEquals("400 BAD_REQUEST \"Username already exists\"", error);

    }

    @Test
    void unauthenticated_access() throws Exception {
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("auto2413", "auto1234", "auto1234");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/password")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(changePasswordDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "testUser")
    void password_change_mismatch() throws Exception {
        // Arrange
        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("auto2413", "auto1234", "auto12345");

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(changePasswordDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();
    }
}
