package com.enigma.lcloanmanajementsystem.service;

import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse create(LoanRequest loanRequest);
    LoanResponse findById(Integer id);
    List<LoanResponse> findAll();
}
