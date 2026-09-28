package com.enigma.lcloanmanajementsystem.dto.request;

import jakarta.validation.constraints.Email;
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
@Schema(description = "Information required to create a customer account.")
public class RegisterRequest {
    @NotBlank(message = "name cannot be empty")
    @Schema(description = "Customer's full name", example = "Ayu Pratama", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    @Schema(description = "Customer's email address", example = "ayu@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "phone number cannot be empty")
    @Schema(description = "Customer's phone number", example = "+6281234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;

    @NotBlank(message = "password cannot be empty")
    @Schema(description = "Account password", example = "StrongPassword123!", format = "password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

//    @NotBlank(message = "role cannot be empty", groups = ValidationGroups.OnCreate.class)
//    @ValidUserRole(groups = ValidationGroups.OnCreate.class)
//    private UserRole role;

}
