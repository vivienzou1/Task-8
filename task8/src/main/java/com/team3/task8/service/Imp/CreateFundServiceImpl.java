package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.CreateCustomerService;
import com.team3.task8.service.CreateFundService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class CreateFundServiceImpl implements CreateFundService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;

    @Autowired
    public CreateFundServiceImpl(UserRepository userRepository, FundRepository fundRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
    }

    @Override
    public ResponseEntity<Object> createFund(HttpSession session, String name, String symbol, String initial_value) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // Parameter invalid
        double initialValue = 0;
        try {
            initialValue = Double.parseDouble(initial_value);
        } catch (NumberFormatException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(result, httpStatus);
        }

        if (session.getAttribute("username") == null) {
            // Not Logged In
            result.put("message", "You are not currently logged in");
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (!userRepository.findByUsername((String) session.getAttribute("username")).getRole().equals("Employee")) {
            // Not employee
            result.put("message", "You must be an employee to perform this action");
            httpStatus = HttpStatus.FORBIDDEN;
        } else {
            // Success Case
            Fund fund = new Fund(name, symbol, initialValue);
            fundRepository.save(fund);
            result.put("message", "The fund was successfully created");
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
