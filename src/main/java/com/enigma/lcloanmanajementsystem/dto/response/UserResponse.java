package com.enigma.lcloanmanajementsystem.dto.response;

import com.enigma.lcloanmanajementsystem.utils.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private UserRole role;

}
