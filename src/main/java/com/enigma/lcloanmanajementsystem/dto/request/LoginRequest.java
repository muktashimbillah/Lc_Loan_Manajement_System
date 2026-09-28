package com.enigma.lcloanmanajementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Credentials used to obtain an access token.")
public class LoginRequest {
    @NotBlank(message = "name cannot be empty")
    @Schema(description = "Registered email address", example = "ayu@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @NotBlank(message = "password cannot be empty")
    @Schema(description = "Account password", example = "StrongPassword123!", format = "password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
