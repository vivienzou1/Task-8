package com.task7.leo.validation.Imp;

import com.task7.leo.repositories.FundRepository;
import com.task7.leo.validation.FundNameExistsCheck;
import com.task7.leo.validation.SymbolFormatCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FundNameExistsCheckImpl implements ConstraintValidator<FundNameExistsCheck, Object> {

    private final FundRepository fundRepository;

    @Autowired
    public FundNameExistsCheckImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Override
    public void initialize(FundNameExistsCheck fundNameExistsCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (fundRepository.findByFundName((String)o) != null){
            return false;
        }
        return true;
    }
}
