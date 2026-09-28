package com.enigma.lcloanmanajementsystem.service;

import com.enigma.lcloanmanajementsystem.dto.request.AdminGetAllLoansRequest;
import com.enigma.lcloanmanajementsystem.dto.request.AdminSearchLoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import org.springframework.data.domain.Page;

public interface AdminLoanService {
    Page<LoanResponse> getAllLoans(AdminGetAllLoansRequest request);
    LoanResponse getLoanById(Long id);
    Page<LoanResponse> search(AdminSearchLoanRequest request);
    LoanResponse updateStatus(Long id, String status);


}
