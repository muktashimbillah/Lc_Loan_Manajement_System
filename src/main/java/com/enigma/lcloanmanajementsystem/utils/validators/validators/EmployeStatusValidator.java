package com.enigma.lcloanmanajementsystem.utils.validators.validators;

import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.validators.ValidEmployeStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeStatusValidator implements ConstraintValidator<ValidEmployeStatus, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return EmployeeStatus.isValid(value);
    }
}
