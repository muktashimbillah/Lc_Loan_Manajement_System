package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.CreditScoringRequest;
import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;
import com.enigma.lcloanmanajementsystem.service.CustomerLoanService;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loans")
@AllArgsConstructor
public class CustomerController {
    private final CustomerLoanService loanService;

//    POST /api/v1/loans
    @PostMapping
    public ResponseEntity<CommonResponse<LoanResponse>> addLoan(@AuthenticationPrincipal UserEntity userEntity, @RequestBody LoanRequest loanRequest) {

        LoanResponse loanResponse = loanService.create(userEntity, loanRequest);
        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                "Succes add loan",
                loanResponse
        );
    }
//    GET  /api/v1/loans/{id}
//    GET  /api/v1/loans/me
}
