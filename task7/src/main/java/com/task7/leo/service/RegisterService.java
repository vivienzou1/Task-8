package com.task7.leo.service;

import com.task7.leo.dto.CustomerRegisterForm;
import com.task7.leo.dto.EmployeeRegisterForm;
import com.task7.leo.dto.UserRegisterForm;

public interface RegisterService {

    void Register(UserRegisterForm form);
    void Register(CustomerRegisterForm form);
    void Register(EmployeeRegisterForm form);
}
