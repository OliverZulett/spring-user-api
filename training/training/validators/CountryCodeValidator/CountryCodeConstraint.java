package com.milankas.training.validators.CountryCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CountryCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryCodeConstraint {

    String message() default "Invalid Country Code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
