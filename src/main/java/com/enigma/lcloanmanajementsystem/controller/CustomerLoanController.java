package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.LoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.UserEntity;
import com.enigma.lcloanmanajementsystem.service.CustomerLoanService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@AllArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
@Tag(name = "Customer Loans", description = "Loan operations available to authenticated customers.")
@SecurityRequirement(name = "bearerAuth")
public class CustomerLoanController {
    private final CustomerLoanService loanService;

//    POST /api/v1/loans
    @PostMapping
    @Operation(summary = "Submit a loan application", description = "Submits a loan application for the authenticated customer.")
    public ResponseEntity<CommonResponse<LoanResponse>> addLoan(@AuthenticationPrincipal UserEntity userEntity, @RequestBody LoanRequest loanRequest) {

        LoanResponse loanResponse = loanService.create(userEntity, loanRequest);
        return ResponseUtil.buildResponse(
                HttpStatus.CREATED,
                ResponseMessage.SUCCES_CREATE_DATA,
                loanResponse
        );
    }

//    GET  /api/v1/loans/me
    @GetMapping("/me")
    @Operation(summary = "List my loans", description = "Returns loan applications owned by the authenticated customer.")
    public ResponseEntity<CommonResponse<List<LoanResponse>>> getMyLoan(@AuthenticationPrincipal UserEntity userEntity) {
        List<LoanResponse> loanResponses = loanService.findAll(userEntity);

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                loanResponses
        );
    }

//    GET  /api/v1/loans/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get my loan", description = "Returns a loan application belonging to the authenticated customer.")
    public ResponseEntity<CommonResponse<LoanResponse>> getLoanDetail(
        @Parameter(hidden = true) @AuthenticationPrincipal UserEntity userEntity,
        @Parameter(description = "Loan identifier", example = "42") @PathVariable Long id) {
        LoanResponse loanResponse = loanService.findById(userEntity, id);

        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                loanResponse
        );
    }


}
