package com.team3.task8.service;

import com.team3.task8.dto.RequestCheckForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface RequestCheckService {

    ResponseEntity<Object> requestCheck(HttpSession session, RequestCheckForm requestCheckForm);
}

