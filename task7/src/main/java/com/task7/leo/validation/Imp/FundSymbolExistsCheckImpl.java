package com.task7.leo.validation.Imp;

import com.task7.leo.repositories.FundRepository;
import com.task7.leo.validation.FundNameExistsCheck;
import com.task7.leo.validation.FundSymbolCheck;
import com.task7.leo.validation.FundSymbolExistsCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FundSymbolExistsCheckImpl implements ConstraintValidator<FundSymbolExistsCheck, Object> {

    private final FundRepository fundRepository;

    @Autowired
    public FundSymbolExistsCheckImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Override
    public void initialize(FundSymbolExistsCheck fundSymbolExistsCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (fundRepository.findByFundSymbol((String)o) != null){
            return false;
        }
        return true;
    }
}
