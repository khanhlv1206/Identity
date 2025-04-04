package com.example.identityservice.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
@Target({ ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(
        validatedBy = {DobValidator.class}
)
public @interface DobConstraint {
    String message() default "{invalid.dob}";
    int min();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
