package com.team3.task8.service;

import com.team3.task8.dto.BuyFundForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface BuyFundService {

    ResponseEntity<Object> buyFund(HttpSession session, BuyFundForm buyFundForm);
}

