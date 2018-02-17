package com.team3.task8.validation.Impl;


import com.team3.task8.validation.CashCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CashCheckImpl implements ConstraintValidator<CashCheck, Object> {
    @Override
    public void initialize(CashCheck cashCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String cash = (String) o;
        } catch (Exception e) {
            return true;
        }

        String cash = "";
        try {
            Double.parseDouble(cash);
        } catch (NumberFormatException e) {
            return false;
        }

        if (cash.split("\\.").length == 1 || cash.split("\\.")[1].length() <= 2)
        {
            return true;
        }

        return false;
    }
}
