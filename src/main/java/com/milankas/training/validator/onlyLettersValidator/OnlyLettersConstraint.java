package com.milankas.training.validator.onlyLettersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyLettersValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyLettersConstraint {

    String message() default "Field must only contain letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
