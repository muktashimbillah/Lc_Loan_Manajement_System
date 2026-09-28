package com.enigma.lcloanmanajementsystem.mappers;

import com.enigma.lcloanmanajementsystem.dto.response.LoanResponse;
import com.enigma.lcloanmanajementsystem.entity.LoanEntity;
import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;

public class LoanMapper {
    public static LoanResponse covertToResponse(LoanEntity entity){
            return LoanResponse.builder()
                    .id(entity.getId())
                    .tenor(entity.getTenor())
                    .purpose(entity.getPurpose())
                    .loanAmount(entity.getLoanAmount())
                    .monthlyIncome(entity.getMonthlyIncome())
                    .monthlyExpenditure(entity.getMonthlyIncome())
                    .employeeStatus(entity.getEmployeeStatus())
                    .creditScore(entity.getCreditScore())
                    .creditRecommendation(entity.getCreditRecommendation())
                    .status(entity.getStatus())
                    .build();
    }
}
