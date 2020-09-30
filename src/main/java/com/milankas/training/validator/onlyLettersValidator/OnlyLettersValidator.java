package com.milankas.training.validator.onlyLettersValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyLettersValidator implements ConstraintValidator<OnlyLettersConstraint, String> {

    @Override
    public void initialize(OnlyLettersConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.matches("[a-zA-Z]+");
    }

}
