package com.enigma.lcloanmanajementsystem.service.Impl;

import com.enigma.lcloanmanajementsystem.dto.request.LoginRequest;
import com.enigma.lcloanmanajementsystem.dto.request.RegisterRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoginResponse;
import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;
import com.enigma.lcloanmanajementsystem.mappers.UserMapper;
import com.enigma.lcloanmanajementsystem.repository.UserRepository;
import com.enigma.lcloanmanajementsystem.service.AuthService;
import com.enigma.lcloanmanajementsystem.utils.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        UserRole role = UserRole.CUSTOMER;
        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        UserEntity userSaved =  userRepository.save(newUser);
        return UserMapper.CovertToResponse(userSaved);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        return null;
    }
}
