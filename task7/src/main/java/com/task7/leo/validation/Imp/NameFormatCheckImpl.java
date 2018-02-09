package com.task7.leo.validation.Imp;

import com.task7.leo.validation.NameFormatCheck;
import com.task7.leo.validation.SymbolFormatCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameFormatCheckImpl implements ConstraintValidator<NameFormatCheck, Object> {

    @Override
    public void initialize(NameFormatCheck nameFormatCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String name = (String) o;
        for (int i = 0; i < name.length(); i++) {
            char tem = name.charAt(i);
            if (tem < 'A' || (tem > 'Z' && tem < 'a') || tem > 'z') return false;
        }

        return true;
    }
}
