package com.task7.leo.validation.Imp;

import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.validation.FundSymbolCheck;
import com.task7.leo.validation.CustomerCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerCheckImpl implements ConstraintValidator<CustomerCheck, Object> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerCheckImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void initialize(CustomerCheck customerCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (customerRepository.findCustomerByUsername((String)o) == null){
            return false;
        }
        return true;
    }
}
