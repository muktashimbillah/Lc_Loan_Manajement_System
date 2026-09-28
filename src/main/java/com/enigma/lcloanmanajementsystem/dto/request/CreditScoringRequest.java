package com.enigma.lcloanmanajementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditScoringRequest {
    private String customerId;
    private Long monthlyIncome;
    private Long monthlyExpense;
    private Long requestedAmount;
    private Integer tenor;
}
