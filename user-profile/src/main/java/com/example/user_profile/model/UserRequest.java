package com.example.user_profile.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.EAN;

@Data
//@Builder
@Schema(
        name = "User",
        description = "Schema to hold User information"
)
public class UserRequest {

    @Schema(
            description = "Name of the user", example = "Hanuman"
    )
    @NotEmpty(message = "Name can not be empty")
    @Size(min=5, max=25, message = "The length of the username should between 5 and 25")
    private String fullName;

    @Schema(
            description = "Email address of the user", example = "hanuman@gmail.com"
    )
    @NotEmpty(message = "Email address can not be empty")
    @Email(message = "Email address should be a valid value")
    @Pattern( regexp = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|[a-zA-Z0-9.-]+\\.(org|in))$", message = "Email must be a valid Gmail address")
    private String email;

    @Schema(
            description = "Password of the user", example = "Hanuman@123"
    )
    @NotEmpty(message = "Password can not be empty")
    @Size(min=5, max=25, message = "The length of the password should between 5 and 25")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain uppercase, lowercase, digit, and special character"
    )
    private String password;
}
