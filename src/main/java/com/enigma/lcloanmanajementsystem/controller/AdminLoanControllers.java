package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.AdminGetAllLoansRequest;
import com.enigma.lcloanmanajementsystem.dto.request.AdminSearchLoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.service.AdminLoanService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/loans")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminLoanControllers {
    private final AdminLoanService adminLoanService;

//    GET   /api/v1/admin/loans
    @GetMapping
    public ResponseEntity<CommonResponse<Page<LoanResponse>>> getAll(@ModelAttribute AdminGetAllLoansRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                adminLoanService.getAllLoans(request)
        );
    }
//    GET   /api/v1/admin/loans/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanResponse>> getById(@PathVariable Long id) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                adminLoanService.getLoanById(id)
        );
    }
//    GET   /api/v1/admin/loans/search
    @GetMapping("/search")
    public ResponseEntity<CommonResponse<Page<LoanResponse>>> search(@ModelAttribute AdminSearchLoanRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                adminLoanService.search(request)
        );
    }
//    PATCH /api/v1/admin/loans/{id}/status
    @PatchMapping("/{id}/status")
    public ResponseEntity<CommonResponse<LoanResponse>> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_UPDATE_DATA,
                adminLoanService.updateStatus(id, status)
        );
    }
}
