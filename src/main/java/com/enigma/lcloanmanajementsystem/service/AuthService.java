package com.enigma.lcloanmanajementsystem.service;

import com.enigma.lcloanmanajementsystem.dto.request.LoginRequest;
import com.enigma.lcloanmanajementsystem.dto.request.RegisterRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoginResponse;
import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;

public interface AuthService {

    UserResponse registerUser(RegisterRequest request);

    LoginResponse loginUser(LoginRequest request);
}
