package com.enigma.lcloanmanajementsystem.controller;

import com.enigma.lcloanmanajementsystem.dto.request.AdminGetAllLoansRequest;
import com.enigma.lcloanmanajementsystem.dto.request.AdminSearchLoanRequest;
import com.enigma.lcloanmanajementsystem.dto.response.CommonResponse;
import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.service.AdminLoanService;
import com.enigma.lcloanmanajementsystem.utils.constants.ResponseMessage;
import com.enigma.lcloanmanajementsystem.utils.helpers.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/loans")
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin Loans", description = "Loan management endpoints available to administrators.")
@SecurityRequirement(name = "bearerAuth")
public class AdminLoanControllers {
    private final AdminLoanService adminLoanService;

//    GET   /api/v1/admin/loans
    @GetMapping
    @Operation(summary = "Get all loans", description = "Returns loan applications using pagination and sorting. Page numbering starts at 0; page size defaults to 10 and is capped at 100.")
    public ResponseEntity<CommonResponse<Page<LoanResponse>>> getAll(@ParameterObject @ModelAttribute AdminGetAllLoansRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                adminLoanService.getAllLoans(request)
        );
    }
//    GET   /api/v1/admin/loans/{id}
    @GetMapping("/{id}")
    @Operation(summary = "Get loan by ID", description = "Returns details for one loan application.")
    public ResponseEntity<CommonResponse<LoanResponse>> getById(
        @Parameter(description = "Loan identifier", example = "42") @PathVariable Long id) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                adminLoanService.getLoanById(id)
        );
    }
//    GET   /api/v1/admin/loans/search
    @GetMapping("/search")
    @Operation(summary = "Search loans", description = "Filters loans by status, employment status, loan amount, and credit score. Supports pagination and sorting.")
    public ResponseEntity<CommonResponse<Page<LoanResponse>>> search(@ParameterObject @ModelAttribute AdminSearchLoanRequest request) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_GET_DATA,
                adminLoanService.search(request)
        );
    }
//    PATCH /api/v1/admin/loans/{id}/status
    @PatchMapping("/{id}/status")
    @Operation(summary = "Update loan status", description = "Changes a loan status. The JSON request body must be one of PENDING, APPROVED, or REJECTED.")
    public ResponseEntity<CommonResponse<LoanResponse>> updateStatus(
        @Parameter(description = "Loan identifier", example = "42") @PathVariable Long id,
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "New loan status",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                schema = @io.swagger.v3.oas.annotations.media.Schema(
                    type = "string",
                    allowableValues = {"PENDING", "APPROVED", "REJECTED"},
                    example = "APPROVED")))
        @RequestBody String status) {
        return ResponseUtil.buildResponse(
                HttpStatus.OK,
                ResponseMessage.SUCCES_UPDATE_DATA,
                adminLoanService.updateStatus(id, status)
        );
    }
}
