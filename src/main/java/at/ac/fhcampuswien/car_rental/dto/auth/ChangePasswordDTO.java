package at.ac.fhcampuswien.car_rental.dto.auth;

import jakarta.validation.constraints.NotEmpty;

public record ChangePasswordDTO(
        @NotEmpty(message = "{inputNotEmpty}")
        String currentPassword,
        @NotEmpty(message = "{inputNotEmpty}")
        String newPassword,
        @NotEmpty(message = "{inputNotEmpty}")
        String verifiedPassword
) {
}
