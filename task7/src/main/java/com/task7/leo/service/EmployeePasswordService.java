package com.task7.leo.service;

import com.task7.leo.dto.EmployeePasswordForm;

public interface EmployeePasswordService {

    void resetPassword(EmployeePasswordForm form, long id);
}
