package com.enigma.lcloanmanajementsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSearchLoanRequest {
    private String status;
    private String employmentStatus;
    private int minimumLoanAmount;
    private int maximumLoanAmount;
    private int minimumCreditScore;
    private int maximumCreditScore;

    private int page;
    private int size;
    private String sortBy;
    private String direction;
}
