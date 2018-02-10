package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import com.team3.task8.service.CreateCustomerService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;

    @Autowired
    public CreateCustomerServiceImpl(UserRepository userRepository, FundRepository fundRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
    }

    @Override
    public ResponseEntity<Object> createCustomer(HttpSession session,
                                                 String fname,
                                          String lname,
                                          String address,
                                          String city,
                                          String state,
                                          String zip,
                                          String email,
                                          String cash,
                                          String username,
                                          String password) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // Parameter invalid
        double cashDouble = 0;
        try {
            cashDouble = Double.parseDouble(cash);
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
        } else if (userRepository.findByUsername(username) != null) {
            // Duplicate username
            result.put("message", "The input you provided is not valid");
            httpStatus = HttpStatus.FORBIDDEN;
        } else {
            // Success Case
            User user = new User(fname, lname, address, city, state, zip, email, cashDouble, "customer", username, password);
            userRepository.save(user);
            result.put("message", "fname was registered successfully");
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
