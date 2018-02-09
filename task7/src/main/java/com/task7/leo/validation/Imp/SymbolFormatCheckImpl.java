package com.task7.leo.validation.Imp;

import com.task7.leo.validation.SymbolFormatCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SymbolFormatCheckImpl implements ConstraintValidator<SymbolFormatCheck, Object> {

    @Override
    public void initialize(SymbolFormatCheck symbolFormatCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String symbol = (String) o;
        for (int i = 0; i < symbol.length(); i++) {
            char tem = symbol.charAt(i);
            if (tem < 'A' || (tem > 'Z' && tem < 'a') || tem > 'z') return false;
        }
        // parameter check

        return true;
    }
}
