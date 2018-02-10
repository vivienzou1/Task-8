package com.team3.task8.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface CreateCustomerService {
    ResponseEntity<Object> createCustomer(HttpSession session,
                                          String fname,
                                          String lname,
                                          String address,
                                          String city,
                                          String state,
                                          String zip,
                                          String email,
                                          String cash,
                                          String username,
                                          String password);
}

