package com.task7.leo.validation.Imp;

import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.EmployeeRepository;
import com.task7.leo.validation.UsernameExistsCheck;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameExistsCheckImpl implements ConstraintValidator<UsernameExistsCheck, Object> {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UsernameExistsCheckImpl(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void initialize(UsernameExistsCheck usernameExistsCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (customerRepository.findCustomerByUsername((String)o) != null){
            return false;
        }
        if (employeeRepository.findEmployeeByUsername((String)o) != null){
            return false;
        }
        return true;
    }
}
