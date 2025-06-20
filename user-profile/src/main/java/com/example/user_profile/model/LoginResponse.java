package com.example.user_profile.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@Schema(
        name = "Response",
        description = "Schema to hold User's login response information"
)
public class LoginResponse {

    @Schema(
            description = "Token of the user to access the APIs"
    )
    private String token;

    @Schema(
            description = "Fullname of the user"
    )
    private String fullName;

    @Schema(
            description = "Role of the user", example = "USER"
    )
    private Set<String> roles;
}
