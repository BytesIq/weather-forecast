package com.example.user_profile.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "Login",
        description = "Schema to hold User's login request information"
)
public class LoginRequest {

    @Schema(
            description = "Email address of the user", example = "abc@gmail.com"
    )
    private String email;

    @Schema(
            description = "Password of the user", example = "Abc@123"
    )
    private String password;
}
