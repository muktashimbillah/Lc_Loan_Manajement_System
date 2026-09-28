package com.enigma.lcloanmanajementsystem.dto.request;

import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Pagination and sorting options for the loan list.")
public class AdminGetAllLoansRequest {
    @Schema(description = "Zero-based page index", example = "0", defaultValue = "0", minimum = "0")
    private int page;
    @Schema(description = "Number of items per page (maximum 100)", example = "10", defaultValue = "10", minimum = "1", maximum = "100")
    private int size;
    @Schema(description = "Sortable field", allowableValues = {"id", "loanAmount", "tenor", "monthlyIncome", "monthlyExpenditure", "employeeStatus", "status", "creditScore"}, example = "id", defaultValue = "")
    private String sortBy;
    @Schema(description = "Sort direction", allowableValues = {"ASC", "DESC"}, example = "DESC", defaultValue = "")
    private String direction;
}
