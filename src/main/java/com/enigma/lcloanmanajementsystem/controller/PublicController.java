package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public")
@Tag(name = "Public", description = "Endpoints that do not require authentication.")
public class PublicController {
    @GetMapping("hello")
    @Operation(summary = "Public health check", description = "Returns a simple greeting without requiring a bearer token.")
    @ApiResponse(responseCode = "200", description = "Greeting returned")
    public ResponseEntity<CommonResponse<String>> helo(){
        return ResponseUtil.buildResponse(HttpStatus.OK, ResponseMessage.SUCCES_GET_DATA, "Hello world!");
    }
}
