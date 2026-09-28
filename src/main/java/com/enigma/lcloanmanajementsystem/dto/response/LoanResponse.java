package com.enigma.lcloanmanajementsystem.dto.response;

import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanResponse {
    private Long id;
    private Integer tenor;
    private String purpose;
    private Long loanAmount;
    private Long monthlyIncome;
    private Long monthlyExpenditure;
    private EmployeeStatus employeeStatus;
    private Integer creditScore;
    private String creditRecommendation;
    private LoanStatus status;
}
