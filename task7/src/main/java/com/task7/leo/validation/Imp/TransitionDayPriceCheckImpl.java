package com.task7.leo.validation.Imp;

import com.task7.leo.validation.TransitionDayPriceCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TransitionDayPriceCheckImpl implements ConstraintValidator<TransitionDayPriceCheck, Object> {

    @Override
    public void initialize(TransitionDayPriceCheck transitionDayPriceCheck) {

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
            double price = 0.0;
            try {
                price = Double.parseDouble(fundPrice);
            } catch (Exception e) {
                return false;
            }

            if (price < 1 || price > 1000) {
                return false;
            }
        }

        return true;
    }
}
