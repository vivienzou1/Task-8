package com.team3.task8.validation.Impl;


import com.team3.task8.validation.CashCheck;
import com.team3.task8.validation.ShareCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ShareCheckImpl implements ConstraintValidator<ShareCheck, Object> {
    @Override
    public void initialize(ShareCheck shareCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String share = (String) o;

        try {
            Integer.parseInt(share);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
