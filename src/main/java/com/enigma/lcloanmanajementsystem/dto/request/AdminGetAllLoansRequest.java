package com.enigma.lcloanmanajementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminGetAllLoansRequest {
    private int page;
    private int size;
    private String sortBy;
    private String direction;
}
