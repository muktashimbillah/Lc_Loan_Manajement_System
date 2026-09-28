package com.enigma.lcloanmanajementsystem.dto.request;


import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Loan filters combined with pagination and sorting options. Amount and score ranges are inclusive.")
public class AdminSearchLoanRequest {
    @Schema(description = "Loan status", allowableValues = {"PENDING", "APPROVED", "REJECTED"}, example = "PENDING")
    private String status;
    @Schema(description = "Employment status", allowableValues = {"EMPLOYED", "SELF_EMPLOYED", "CONTRACT", "UNEMPLOYED"}, example = "EMPLOYED")
    private String employmentStatus;
    @Schema(description = "Minimum requested loan amount", example = "1000000", minimum = "0")
    private int minimumLoanAmount;
    @Schema(description = "Maximum requested loan amount", example = "50000000", minimum = "0")
    private int maximumLoanAmount;
    @Schema(description = "Minimum credit score", example = "300", minimum = "0")
    private int minimumCreditScore;
    @Schema(description = "Maximum credit score", example = "850", minimum = "0")
    private int maximumCreditScore;

    @Schema(description = "Zero-based page index", example = "0", defaultValue = "0", minimum = "0")
    private int page;
    @Schema(description = "Number of items per page (maximum 100)", example = "10", defaultValue = "10", minimum = "1", maximum = "100")
    private int size;
    @Schema(description = "Sortable field", allowableValues = {"id", "loanAmount", "tenor", "monthlyIncome", "monthlyExpenditure", "employeeStatus", "status", "creditScore"}, example = "creditScore", defaultValue = "id")
    private String sortBy;
    @Schema(description = "Sort direction", allowableValues = {"ASC", "DESC"}, example = "DESC", defaultValue = "ASC")
    private String direction;
}
