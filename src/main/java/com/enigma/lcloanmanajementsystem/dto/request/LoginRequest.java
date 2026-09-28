package com.enigma.lcloanmanajementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "name cannot be empty")
    private String email;
    @NotBlank(message = "password cannot be empty")
    private String password;
}
