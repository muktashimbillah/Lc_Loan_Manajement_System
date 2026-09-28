package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.RegisterRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;
import com.enigma.lcloanmanajementsystem.service.AuthService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private AuthService authService;

//    POST /api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<CommonResponse<UserResponse>> register (@RequestBody RegisterRequest request){
        UserResponse userResponse = authService.registerUser(request);

        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                ResponseMessage.SUCCES_CREATE_DATA,
                userResponse
        );
    }
//    POST /api/v1/auth/login
}
