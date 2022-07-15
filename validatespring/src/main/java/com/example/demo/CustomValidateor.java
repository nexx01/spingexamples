package com.example.demo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidateor implements ConstraintValidator<CustomValidateAnnotation,DTOCustomValidate> {
    @Override
    public boolean isValid(DTOCustomValidate value, ConstraintValidatorContext context) {

        boolean b = value.name != null && value.surname != null;

        return b;
    }
}
