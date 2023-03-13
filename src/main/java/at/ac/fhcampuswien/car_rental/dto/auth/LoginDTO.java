package at.ac.fhcampuswien.car_rental.dto.auth;


import javax.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty(message = "No username provided")
        String userName,
        @NotEmpty(message = "No password provided")
        String password
) {
}
