package com.enigma.lcloanmanajementsystem.service;

import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;

import java.util.List;

public interface CustomerLoanService {
    LoanResponse create(UserEntity user,  LoanRequest loanRequest);
    LoanResponse findById(UserEntity user, Long id);
    List<LoanResponse> findAll(UserEntity user);
}
