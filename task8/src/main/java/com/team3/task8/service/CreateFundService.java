package com.team3.task8.service;

import com.team3.task8.dto.CreateFundForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface CreateFundService {

    ResponseEntity<Object> createFund(HttpSession session, CreateFundForm createFundForm);
}

