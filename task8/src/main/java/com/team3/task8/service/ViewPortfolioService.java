package com.team3.task8.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface ViewPortfolioService {

    ResponseEntity<Object> viewPortfolio(HttpSession session);
}

