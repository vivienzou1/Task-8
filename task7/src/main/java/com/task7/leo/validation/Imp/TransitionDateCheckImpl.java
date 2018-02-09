package com.task7.leo.validation.Imp;

import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Employee;
import com.task7.leo.domain.Fund;
import com.task7.leo.dto.TransitionDayForm;
import com.task7.leo.dto.UserRegisterForm;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.validation.TransitionDateCheck;
import com.task7.leo.validation.TransitionSymbolCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.List;

public class TransitionDateCheckImpl implements ConstraintValidator<TransitionDateCheck, Object> {
    private final FundRepository fundRepository;

    @Autowired
    public TransitionDateCheckImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }


    @Override
    public void initialize(TransitionDateCheck transitionDateCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Date date = (Date) o;
        Fund fund = fundRepository.findById(1);

        if (fund != null && fund.getLastTransition() != null){
            Date lastDate = fund.getLastTransition();
            if (date.compareTo(lastDate) <= 0) {
                return false;
            }
        }
        return true;
    }
}
