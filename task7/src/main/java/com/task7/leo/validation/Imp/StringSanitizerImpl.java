package com.task7.leo.validation.Imp;

import com.task7.leo.validation.StringSanitizer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringSanitizerImpl implements ConstraintValidator<StringSanitizer, Object> {

    @Override
    public void initialize(StringSanitizer stringSanitizer) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String string = (String) o;
        if (string == null) return true;
        if (string.contains("<") || string.contains(">") || string.contains("&") || string.contains("\"") || string.contains(";")) {
            return false;
        }

        return true;
    }
}
