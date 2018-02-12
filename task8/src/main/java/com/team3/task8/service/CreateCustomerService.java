package com.team3.task8.service;

import com.team3.task8.dto.CreateCustomerForm;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface CreateCustomerService {

    ResponseEntity<Object> createCustomer(HttpSession session, CreateCustomerForm createCustomerForm);
}

