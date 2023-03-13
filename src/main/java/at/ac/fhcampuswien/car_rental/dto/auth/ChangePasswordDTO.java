package at.ac.fhcampuswien.car_rental.dto.auth;


import javax.validation.constraints.NotEmpty;

public record ChangePasswordDTO(
        @NotEmpty(message = "No current password set")
        String currentPassword,
        @NotEmpty(message = "No new password set")
        String newPassword,
        @NotEmpty(message = "No verified password set")
        String verifiedPassword
) {
}
