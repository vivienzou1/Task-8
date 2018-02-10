package com.team3.task8.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface SellFundService {
    ResponseEntity<Object> sellFund(HttpSession session, String symbol, String numShares);
}

