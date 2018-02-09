package com.task7.leo.validation.Imp;

import com.task7.leo.validation.TransitionFundCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TransitionFundCheckImpl implements ConstraintValidator<TransitionFundCheck, Object> {

    @Override
    public void initialize(TransitionFundCheck transitionFundCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        List<String> fundPrices;
        if (o instanceof List) {
            fundPrices = (List<String>) o;
        }  else {
            return false;
        }

        for (String fundPrice : fundPrices) {
            try {
                Double.parseDouble(fundPrice);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
