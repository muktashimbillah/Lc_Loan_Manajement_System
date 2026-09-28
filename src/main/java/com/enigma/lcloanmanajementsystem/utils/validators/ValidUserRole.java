package com.enigma.lcloanmanajementsystem.utils.validators;

import com.enigma.lcloanmanajementsystem.utils.validators.validators.UserRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserRoleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserRole {
    String message() default "Format role tidak sesuai";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
