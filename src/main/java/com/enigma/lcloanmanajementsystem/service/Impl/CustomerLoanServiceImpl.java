package com.enigma.lcloanmanajementsystem.service.Impl;

import com.enigma.lcloanmanajementsystem.client.LmsClient;
import com.enigma.lcloanmanajementsystem.dto.request.CreditScoringRequest;
import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CreditScoringResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.LoanEntity;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;
import com.enigma.lcloanmanajementsystem.mappers.LoanMapper;
import com.enigma.lcloanmanajementsystem.repository.LoanRepository;
import com.enigma.lcloanmanajementsystem.service.CustomerLoanService;
import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerLoanServiceImpl implements CustomerLoanService {
    private final LoanRepository loanRepository;
    private final LmsClient lmsClinet;

    @Override
    public LoanResponse create(UserEntity user, LoanRequest loanRequest) {
        CreditScoringRequest creditScoringRequest = CreditScoringRequest.builder()
                .customerId(user.getId())
                .monthlyIncome(loanRequest.getMonthlyIncome())
                .monthlyExpense(loanRequest.getMonthlyExpenditure())
                .requestedAmount(loanRequest.getLoanAmount())
                .tenor(loanRequest.getTenor())
                .build();

       CreditScoringResponse creditScoringResponse = lmsClinet.getCreditScore(creditScoringRequest);

        LoanEntity newLoan = LoanEntity.builder()
                .tenor(loanRequest.getTenor())
                .user(user)
                .loanAmount(loanRequest.getLoanAmount())
                .purpose(loanRequest.getPurpose())
                .monthlyIncome(loanRequest.getMonthlyIncome())
                .monthlyExpenditure(loanRequest.getMonthlyExpenditure())
                .employeeStatus(EmployeeStatus.valueOf(loanRequest.getEmployeeStatus()))

                .creditScore(creditScoringResponse.getScore())
                .creditRecommendation(creditScoringResponse.getRiskLevel())
                .status(LoanStatus.PENDING)
                .build();

        LoanEntity loan =  loanRepository.save(newLoan);

        return LoanMapper.covertToResponse(loan);
    }

    @Override
    public LoanResponse findById(UserEntity user, Long id) {
        LoanEntity loan = loanRepository.findByIdAndUserId(id, user.getId());
        return LoanMapper.covertToResponse(loan);
    }

    @Override
    public List<LoanResponse> findAll(UserEntity user) {
        List<LoanEntity> loanEntities = loanRepository.findByUserId(user.getId());
        return LoanMapper.covertToResponseList(loanEntities);
    }
}
