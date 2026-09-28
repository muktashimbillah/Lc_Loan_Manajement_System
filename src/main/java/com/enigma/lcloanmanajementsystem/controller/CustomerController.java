package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loans")
@AllArgsConstructor
public class CustomerController {
    private final LoanService loanService;

//    POST /api/v1/loans
    @PostMapping
    public String addLoan(LoanRequest loanRequest) {
        return "aman";
    }
//    GET  /api/v1/loans/{id}
//    GET  /api/v1/loans/me
}
