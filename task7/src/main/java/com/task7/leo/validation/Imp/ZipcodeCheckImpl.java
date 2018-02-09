package com.task7.leo.validation.Imp;

import com.task7.leo.validation.ZipcodeCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipcodeCheckImpl implements ConstraintValidator<ZipcodeCheck, Object> {
    @Override
    public void initialize(ZipcodeCheck zipcodeCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String zip = (String) o;
        try {
            Integer.parseInt(zip);
        } catch (Exception e) {
            return false;
        }
        if (zip.length() != 5) return false;
        return true;
    }
}
