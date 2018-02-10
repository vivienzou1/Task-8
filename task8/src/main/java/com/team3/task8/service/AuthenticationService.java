package com.team3.task8.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface AuthenticationService {
    ResponseEntity<Object> login(HttpSession session, String username, String password);
    ResponseEntity<Object> logout(HttpSession session);
}

