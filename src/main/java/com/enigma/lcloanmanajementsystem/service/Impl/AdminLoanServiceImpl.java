package com.enigma.lcloanmanajementsystem.service.Impl;

import com.enigma.lcloanmanajementsystem.dto.request.AdminGetAllLoansRequest;
import com.enigma.lcloanmanajementsystem.dto.request.AdminSearchLoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.LoanEntity;
import com.enigma.lcloanmanajementsystem.mappers.LoanMapper;
import com.enigma.lcloanmanajementsystem.repository.LoanRepository;
import com.enigma.lcloanmanajementsystem.service.AdminLoanService;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;
import com.enigma.lcloanmanajementsystem.utils.exceptions.BusinessException;
import com.enigma.lcloanmanajementsystem.utils.exceptions.ResourceNotFoundException;
import com.enigma.lcloanmanajementsystem.utils.helpers.PagenationUtil;
import com.enigma.lcloanmanajementsystem.utils.specifications.LoanSearchSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminLoanServiceImpl implements AdminLoanService {
    private static final Set<String> SORTABLE_FIELDS = Set.of(
            "id", "loanAmount", "tenor", "monthlyIncome", "monthlyExpenditure",
            "employeeStatus", "status", "creditScore"
    );

    private final LoanRepository loanRepository;

    @Override
    public Page<LoanResponse> getAllLoans(AdminGetAllLoansRequest request) {
        if (!SORTABLE_FIELDS.contains(request.getSortBy())) {
            throw  new BusinessException("Invalid sort by: " + request.getSortBy());
        }
        Pageable pageable = PagenationUtil.createPageable(request.getPage(), request.getSize(), request.getSortBy(), request.getDirection());
        return loanRepository.findAll(pageable)
                .map(LoanMapper::covertToResponse);
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        LoanEntity loan= loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found: " + id));
        return LoanMapper.covertToResponse(loan);
    }

    @Override
    public Page<LoanResponse> search(AdminSearchLoanRequest request) {
        if (!SORTABLE_FIELDS.contains(request.getSortBy())) {
            throw  new BusinessException("Invalid sort by: " + request.getSortBy());
        }
        Pageable pageable = PagenationUtil.createPageable(request.getPage(), request.getSize(), request.getSortBy(), request.getDirection());
        return loanRepository.findAll(LoanSearchSpecification.getLoanSpecification(request), pageable)
                .map(LoanMapper::covertToResponse);
    }

    @Override
    @Transactional
    public LoanResponse updateStatus(Long id, String status) {
        if (!LoanStatus.isValid(status)) {
            throw new BusinessException("Invalid status name");
        }

        LoanStatus loanStatus = LoanStatus.valueOf(status);

        LoanEntity loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found: " + id));

        if (!loan.getStatus().equals(LoanStatus.PENDING)) {
            throw new BusinessException("Invalid loan status: " + status);
        }
        loan.setStatus(loanStatus);
        return LoanMapper.covertToResponse(loanRepository.save(loan));
    }

    private LoanEntity findLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found: " + id));
    }
    
}
