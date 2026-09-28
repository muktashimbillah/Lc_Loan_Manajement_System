package com.enigma.lcloanmanajementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditScoringResponse {
    private String customerId;
    private Integer score;
    private String riskLevel;      // LOW / MEDIUM / HIGH
    private Boolean eligible;
}
