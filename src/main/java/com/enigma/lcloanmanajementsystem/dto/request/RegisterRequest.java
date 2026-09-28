package com.enigma.lcloanmanajementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "phone number cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "password cannot be empty")
    private String password;

//    @NotBlank(message = "role cannot be empty", groups = ValidationGroups.OnCreate.class)
//    @ValidUserRole(groups = ValidationGroups.OnCreate.class)
//    private UserRole role;

}
