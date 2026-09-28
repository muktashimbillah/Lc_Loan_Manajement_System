package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;
import com.enigma.lcloanmanajementsystem.service.CustomerLoanService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@AllArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerLoanController {
    private final CustomerLoanService loanService;

//    POST /api/v1/loans
    @PostMapping
    public ResponseEntity<CommonResponse<LoanResponse>> addLoan(@AuthenticationPrincipal UserEntity userEntity, @RequestBody LoanRequest loanRequest) {

        LoanResponse loanResponse = loanService.create(userEntity, loanRequest);
        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                ResponseMessage.SUCCES_CREATE_DATA,
                loanResponse
        );
    }

//    GET  /api/v1/loans/me
    @GetMapping("/me")
    public ResponseEntity<CommonResponse<List<LoanResponse>>> getMyLoan(@AuthenticationPrincipal UserEntity userEntity) {
        List<LoanResponse> loanResponses = loanService.findAll(userEntity);

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                loanResponses
        );
    }

//    GET  /api/v1/loans/{id}
    @GetMapping("/{id}")
    public  ResponseEntity<CommonResponse<LoanResponse>> getLoanDetail(@AuthenticationPrincipal UserEntity userEntity, @PathVariable Long id) {
        LoanResponse loanResponse = loanService.findById(userEntity, id);

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                loanResponse
        );
    }


}
