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
import com.enigma.lcloanmanajementsystem.utils.specifications.LoanSearchSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminLoanServiceImpl implements AdminLoanService {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 100;
    private static final Set<String> SORTABLE_FIELDS = Set.of(
            "id", "loanAmount", "tenor", "monthlyIncome", "monthlyExpenditure",
            "employeeStatus", "status", "creditScore"
    );

    private final LoanRepository loanRepository;

    @Override
    public Page<LoanResponse> getAllLoans(AdminGetAllLoansRequest request) {
        return loanRepository.findAll(createPageable(request.getPage(), request.getSize(), request.getSortBy(), request.getDirection()))
                .map(LoanMapper::covertToResponse);
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        return LoanMapper.covertToResponse(findLoan(id));
    }

    @Override
    public Page<LoanResponse> search(AdminSearchLoanRequest request) {
        Pageable pageable = createPageable(request.getPage(), request.getSize(), request.getSortBy(), request.getDirection());
        return loanRepository.findAll(LoanSearchSpecification.getLoanSpecification(request), pageable)
                .map(LoanMapper::covertToResponse);
    }

    @Override
    @Transactional
    public LoanResponse updateStatus(Long id, String status) {
        LoanStatus loanStatus;
        try {
            loanStatus = LoanStatus.valueOf(status.trim().toUpperCase(Locale.ROOT));
        } catch (NullPointerException | IllegalArgumentException exception) {
            throw new BusinessException("Invalid loan status: " + status);
        }

        LoanEntity loan = findLoan(id);
        loan.setStatus(loanStatus);
        return LoanMapper.covertToResponse(loanRepository.save(loan));
    }

    private LoanEntity findLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found: " + id));
    }

    private Pageable createPageable(int page, int size, String sortBy, String direction) {
        int safePage = Math.max(page, 0);
        int safeSize = size <= 0 ? DEFAULT_PAGE_SIZE : Math.min(size, MAX_PAGE_SIZE);
        String safeSortBy = SORTABLE_FIELDS.contains(sortBy) ? sortBy : "id";
        Sort.Direction sortDirection = "DESC".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(safePage, safeSize, Sort.by(sortDirection, safeSortBy));
    }
}
