package at.ac.fhcampuswien.car_rental.dto.auth;


import javax.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty(message = "{inputNotEmpty}")
        String userName,
        @NotEmpty(message = "{inputNotEmpty}")
        String password
) {
}
