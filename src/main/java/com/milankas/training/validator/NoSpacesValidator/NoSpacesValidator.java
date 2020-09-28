package com.milankas.training.validator.NoSpacesValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpacesValidator implements ConstraintValidator<NoSpacesConstraint, String> {

    @Override
    public void initialize(NoSpacesConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.replaceAll("\\s+", "").length() > 0;
    }

}
