package com.enigma.lcloanmanajementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequest {
    @NotBlank( message = "tenor cannot be empty")
    private Integer tenor;
    @NotBlank(message = "purpose cannot be empty")
    private String purpose;
    @NotBlank(message = "loan ammount cannot be empty")
    private Long loanAmount;
    @NotBlank(message = "monthly income cannot be empty")
    private Long monthlyIncome;
    @NotBlank(message = "monthly expenditure cannot be empty")
    private Long monthlyExpenditure;
    @NotBlank(message = "employe satus cannot be empty")
    private String employeeStatus;
}
