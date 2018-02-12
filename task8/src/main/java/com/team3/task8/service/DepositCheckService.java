package com.team3.task8.service;

import com.team3.task8.dto.DepositCheckForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface DepositCheckService {

    ResponseEntity<Object> depositCheck(HttpSession session, DepositCheckForm depositCheckForm);
}

