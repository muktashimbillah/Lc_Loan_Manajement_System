package com.enigma.lcloanmanajementsystem.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreditScoringRequest {
    private Long customerId;
    private Long monthlyIncome;
    private Long monthlyExpense;
    private Long requestedAmount;
    private Integer tenor;
}
