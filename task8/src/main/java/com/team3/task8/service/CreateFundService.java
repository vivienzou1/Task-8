package com.team3.task8.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface CreateFundService {
    ResponseEntity<Object> createFund(HttpSession session, String name, String symbol, String initial_value);
}

