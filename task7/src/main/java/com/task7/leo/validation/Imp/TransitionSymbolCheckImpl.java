package com.task7.leo.validation.Imp;

import com.task7.leo.repositories.FundRepository;
import com.task7.leo.validation.TransitionSymbolCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TransitionSymbolCheckImpl implements ConstraintValidator<TransitionSymbolCheck, Object> {
    private final FundRepository fundRepository;

    @Autowired
    public TransitionSymbolCheckImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }


    @Override
    public void initialize(TransitionSymbolCheck transitionSymbolCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        List<String> symbols;
        if (o instanceof List) {
            symbols = (List<String>) o;
        }  else {
            return false;
        }

        // fund exists
        for (String symbol : symbols) {
            if (fundRepository.findByFundSymbol(symbol) == null) {
                return false;
            }
        }

        return true;
    }
}
