package com.enigma.lcloanmanajementsystem.utils.validators.validators;

import com.enigma.lcloanmanajementsystem.utils.enums.UserRole;
import com.enigma.lcloanmanajementsystem.utils.validators.ValidUserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserRoleValidator implements ConstraintValidator<ValidUserRole, String> {
    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context
    ) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        return UserRole.isValid(value);
    }
}
