package com.example.user_profile.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponse {

    @Schema(
            description = "The time representing when the error happened"
    )
    private LocalDateTime localDateTime;

    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    @Schema(
            description = "API path invoked by client"
    )
    private String errorDescription;

    public ErrorResponse(LocalDateTime localDateTime, String errorMessage, String errorDescription) {
        this.localDateTime = localDateTime;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }
}
