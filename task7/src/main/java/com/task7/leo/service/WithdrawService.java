package com.task7.leo.service;

import com.task7.leo.dto.WithdrawForm;

public interface WithdrawService {

    void withdraw(WithdrawForm form, String username);
}
