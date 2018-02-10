package com.team3.task8.service;

import com.team3.task8.domain.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface BuyFundService {
    ResponseEntity<Object> buyFund(HttpSession session, String symbol, String cacheValue);
}

