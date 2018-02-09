package com.task7.leo.service;

import com.task7.leo.dto.CustomerPasswordForm;

public interface CustomerPasswordService {

    void resetPassword(CustomerPasswordForm form, long id);
    void resetPasswordByUsername(CustomerPasswordForm form, String username);
}
