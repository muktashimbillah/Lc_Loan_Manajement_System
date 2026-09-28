package com.enigma.lcloanmanajementsystem.utils.specifications;

import com.enigma.lcloanmanajementsystem.dto.request.AdminGetAllLoansRequest;
import com.enigma.lcloanmanajementsystem.dto.request.AdminSearchLoanRequest;
import com.enigma.lcloanmanajementsystem.entity.LoanEntity;
import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;
import com.enigma.lcloanmanajementsystem.utils.exceptions.BusinessException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoanSearchSpecification {
    public static Specification<LoanEntity> getLoanSpecification(AdminSearchLoanRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(request.getStatus())) {
                predicates.add(criteriaBuilder.equal(
                        root.get("status"), parseEnum(request.getStatus(), LoanStatus.class, "status")
                ));
            }
            if (StringUtils.hasText(request.getEmploymentStatus())) {
                predicates.add(criteriaBuilder.equal(
                        root.get("employeeStatus"), parseEnum(request.getEmploymentStatus(), EmployeeStatus.class, "employmentStatus")
                ));
            }
            if (request.getMinimumLoanAmount() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("loanAmount"), (long) request.getMinimumLoanAmount()
                ));
            }
            if (request.getMaximumLoanAmount() > 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("loanAmount"), (long) request.getMaximumLoanAmount()
                ));
            }
            if (request.getMinimumCreditScore() > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("creditScore"), request.getMinimumCreditScore()
                ));
            }
            if (request.getMaximumCreditScore() > 0) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("creditScore"), request.getMaximumCreditScore()
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <E extends Enum<E>> E parseEnum(String value, Class<E> enumType, String fieldName) {
        try {
            return Enum.valueOf(enumType, value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new BusinessException("Invalid " + fieldName + ": " + value);
        }
    }
}
