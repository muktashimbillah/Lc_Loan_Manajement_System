package com.enigma.lcloanmanajementsystem.service.Impl;

import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.repository.LoanRepository;
import com.enigma.lcloanmanajementsystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    @Override
    public LoanResponse create(LoanRequest loanRequest) {
        return null;
    }

    @Override
    public LoanResponse findById(Integer id) {
        return null;
    }

    @Override
    public List<LoanResponse> findAll() {
        return List.of();
    }
}
