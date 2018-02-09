package com.task7.leo.validation.Imp;

import com.task7.leo.dto.BuyForm;
import com.task7.leo.dto.CustomerRegisterForm;
import com.task7.leo.dto.SellForm;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.validation.FundSymbolCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FundSymbolCheckImpl implements ConstraintValidator<FundSymbolCheck, Object> {

    private final FundRepository fundRepository;

    @Autowired
    public FundSymbolCheckImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }


    @Override
    public void initialize(FundSymbolCheck fundSymbolCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (fundRepository.findByFundSymbol((String)o) == null){
            return false;
        }
        return true;
    }
}
