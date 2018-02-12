package com.team3.task8.service;

import com.team3.task8.dto.SellFundForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface SellFundService {

    ResponseEntity<Object> sellFund(HttpSession session, SellFundForm sellFundForm);
}

