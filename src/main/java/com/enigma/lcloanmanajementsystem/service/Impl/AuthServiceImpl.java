package com.enigma.lcloanmanajementsystem.service.Impl;

import com.enigma.lcloanmanajementsystem.dto.request.LoginRequest;
import com.enigma.lcloanmanajementsystem.dto.request.RegisterRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoginResponse;
import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;
import com.enigma.lcloanmanajementsystem.mappers.UserMapper;
import com.enigma.lcloanmanajementsystem.repository.UserRepository;
import com.enigma.lcloanmanajementsystem.security.JwtTokenService;
import com.enigma.lcloanmanajementsystem.service.AuthService;
import com.enigma.lcloanmanajementsystem.utils.enums.UserRole;
import com.enigma.lcloanmanajementsystem.utils.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtToken;

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        UserRole role = UserRole.CUSTOMER;
        UserEntity newUser = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        UserEntity userSaved =  userRepository.save(newUser);
        return UserMapper.CovertToResponse(userSaved);
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate jwt token
        String jwt = jwtToken.generateJwtToken(auth);

        // get role
        String role = jwtToken.getRoleFromJwtToken(jwt);

        // return response
        return LoginResponse.builder()
                .token(jwt)
                .role(role)
                .email(request.getEmail())
                .build();
    }
}
