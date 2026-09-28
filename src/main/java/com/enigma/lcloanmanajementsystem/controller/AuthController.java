package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.RegisterRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.UserResponse;
import com.enigma.lcloanmanajementsystem.service.AuthService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name="Auth", description = "Auth endpoint")
public class AuthController {
    private final AuthService authService;

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

    @GetMapping("hello")
    public ResponseEntity<CommonResponse<String>> helo(){
        return ResponseUtil.buildResponse(HttpStatus.OK, ResponseMessage.SUCCES_GET_DATA, "Hello world!");
    }
//    POST /api/v1/auth/login
}
