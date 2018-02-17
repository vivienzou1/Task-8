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
        String cash;
//        System.out.println((String) o);
//        System.out.println(o == null);
        if (o == null) {
            return true;
        }

        try {
            cash = (String) o;
        } catch (Exception e) {
//            System.out.println("exception here!!!");
            return true;
        }


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
