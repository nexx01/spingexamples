package com.example.demo;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ProgrammaticallyValidatingService {

    private Validator validator;

    public ProgrammaticallyValidatingService(Validator validator) {
        this.validator = validator;
    }

    public void validateInputWithInjectedValidator(PersonDto personDto) {
        Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}