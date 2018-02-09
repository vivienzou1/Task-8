package com.task7.leo.validation.Imp;

import com.task7.leo.dto.*;
import com.task7.leo.validation.ConfirmPasswordCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordCheckImpl implements ConstraintValidator<ConfirmPasswordCheck, Object> {
    @Override
    public void initialize(ConfirmPasswordCheck confirmPasswordCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o.getClass() == CustomerRegisterForm.class) {
            CustomerRegisterForm customerRegisterForm = (CustomerRegisterForm) o;
            if (customerRegisterForm.getPassword().equals(customerRegisterForm.getConfirmPassword())) {
                return true;
            }
        } else if (o.getClass() == EmployeeRegisterForm.class) {
            EmployeeRegisterForm employeeRegisterForm = (EmployeeRegisterForm) o;
            if (employeeRegisterForm.getPassword().equals(employeeRegisterForm.getConfirmPassword())) {
                return true;
            }
        } else if (o.getClass() == CustomerPasswordForm.class) {
            CustomerPasswordForm customerPasswordForm = (CustomerPasswordForm) o;
            if (customerPasswordForm.getPassword().equals(customerPasswordForm.getConfirmPassword())) {
                return true;
            }
        } else if (o.getClass() == EmployeePasswordForm.class) {
            EmployeePasswordForm employeePasswordForm = (EmployeePasswordForm) o;
            if (employeePasswordForm.getPassword().equals(employeePasswordForm.getConfirmPassword())) {
                return true;
            }
        } else if (o.getClass() == UserRegisterForm.class) {
            UserRegisterForm userRegisterForm = (UserRegisterForm) o;
            if (userRegisterForm.getPassword().equals(userRegisterForm.getConfirmPassword())) {
                return true;
            }
        }
        return false;
    }
}
