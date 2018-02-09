package com.task7.leo.validation.Imp;

import com.task7.leo.validation.DateFormatCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class DateFormatCheckImpl implements ConstraintValidator<DateFormatCheck, Object> {


    @Override
    public void initialize(DateFormatCheck dateFormatCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Date date = (Date) o;
        if (date == null) return true;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DATE);
        if (year > 9999 || year < 1000) {
            return false;
        }
        if (month > 11 || month < 0) {
            return false;
        }
        if (day > 31 || day < 1) {
            return false;
        }
        return true;
    }
}
