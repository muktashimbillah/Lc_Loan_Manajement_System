package com.enigma.lcloanmanajementsystem.mappers;

import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;

public class UserMapper {
    public static UserResponse CovertToResponse(UserEntity user){
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setPassword(user.getPassword());
        userResponse.setRole(user.getRole());

        return userResponse;

    }
}
