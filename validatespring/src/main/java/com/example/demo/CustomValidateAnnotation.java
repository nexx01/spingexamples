package com.example.demo;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Payload;
@Target({ ElementType.PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = CustomValidateor.class)
public @interface CustomValidateAnnotation {

    String message() default "{CapitalLetter  invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
