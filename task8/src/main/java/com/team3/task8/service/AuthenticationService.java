package com.team3.task8.service;

import com.team3.task8.dto.LoginForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface AuthenticationService {

    ResponseEntity<Object> login(HttpSession session, LoginForm loginForm);

    ResponseEntity<Object> logout(HttpSession session);
}

