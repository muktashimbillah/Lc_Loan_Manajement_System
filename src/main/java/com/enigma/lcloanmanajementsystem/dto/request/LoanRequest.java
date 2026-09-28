package com.enigma.lcloanmanajementsystem.dto.request;

import com.enigma.lcloanmanajementsystem.utils.validators.ValidEmployeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Customer loan application details.")
public class LoanRequest {
    @NotBlank( message = "tenor cannot be empty")
    @Schema(description = "Loan term in months", example = "24", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer tenor;
    @NotBlank(message = "purpose cannot be empty")
    @Schema(description = "Purpose of the loan", example = "Home renovation", requiredMode = Schema.RequiredMode.REQUIRED)
    private String purpose;
    @NotBlank(message = "loan ammount cannot be empty")
    @Schema(description = "Requested loan amount", example = "50000000", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long loanAmount;
    @NotBlank(message = "monthly income cannot be empty")
    @Schema(description = "Customer's monthly income", example = "12000000", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long monthlyIncome;
    @NotBlank(message = "monthly expenditure cannot be empty")
    @Schema(description = "Customer's monthly expenditure", example = "5000000", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long monthlyExpenditure;
    @NotBlank(message = "employe satus cannot be empty")
    @ValidEmployeStatus
    @Schema(description = "Employment status", allowableValues = {"EMPLOYED", "SELF_EMPLOYED", "CONTRACT", "UNEMPLOYED"}, example = "EMPLOYED", requiredMode = Schema.RequiredMode.REQUIRED)
    private String employeeStatus;
}
