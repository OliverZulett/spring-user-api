package com.milankas.training.validator.NoSpacesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoSpacesValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpacesConstraint {

    String message() default "Field is empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
