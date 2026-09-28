package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.LoginRequest;
import com.enigma.lcloanmanajementsystem.dto.request.RegisterRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoginResponse;
import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;
import com.enigma.lcloanmanajementsystem.service.AuthService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register an account and obtain an access token.")
public class AuthController {
    private final AuthService authService;

//    POST /api/v1/auth/register
    @PostMapping("/register")
        @Operation(summary = "Register a customer", description = "Creates a customer account. Registration is public and does not require a bearer token.")
    public ResponseEntity<CommonResponse<UserResponse>> register (@RequestBody RegisterRequest request){
        UserResponse userResponse = authService.registerUser(request);

        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                ResponseMessage.SUCCES_CREATE_DATA,
                userResponse
        );
    }

//    POST /api/v1/auth/login
    @PostMapping("/login")
        @Operation(summary = "Log in", description = "Authenticates a user and returns a JWT access token.")
    public  ResponseEntity<CommonResponse<LoginResponse>> login (@RequestBody LoginRequest request) throws Exception {
        LoginResponse loginResponse = authService.loginUser(request);

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_LOGIN,
                loginResponse
        );
    }
}
