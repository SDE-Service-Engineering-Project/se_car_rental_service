package at.ac.fhcampuswien.car_rental.dto.auth;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public record RegisterDTO(
        @NotEmpty(message = "No username provided")
        @Size(min = 3, max = 32, message = "Username must be between {min} and {max} characters")
        String userName,

        @NotEmpty(message = "No first name provided")
        @Size(min = 3, max = 30, message = "First name must be between {min} and {max} characters")
        String firstName,

        @NotEmpty(message = "No last name provided")
        @Size(min = 3, max = 30, message = "Last name must be between {min} and {max} characters")
        String lastName,

        @NotEmpty(message = "No password provided")
        @Size(min = 8, max = 30, message = "Password must be between {min} and {max} characters")
        String password) {
}
