package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class BuyFundServiceImpl implements BuyFundService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;

    @Autowired
    public BuyFundServiceImpl(UserRepository userRepository, FundRepository fundRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
    }

    @Override
    public ResponseEntity<Object> buyFund(HttpSession session, String symbol, String cashValue) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // Parameter invalid ???
//        httpStatus = HttpStatus.BAD_REQUEST;

        if (session.getAttribute("username") == null) {
            // Not Logged In
            result.put("message", "You are not currently logged in");
            httpStatus = HttpStatus.FORBIDDEN;
        } else {
            User user = userRepository.findByUsername((String) session.getAttribute("username"));
            if (!user.getRole().equals("customer")) {
                // Not employee
                result.put("message", "You must be an customer to perform this action");
                httpStatus = HttpStatus.FORBIDDEN;
            } else if (user.getCash() < Double.parseDouble(cashValue)) {
                // Not enough cash in account
                result.put("message", "You don’t have enough cash in your account to make this purchase");
                httpStatus = HttpStatus.FORBIDDEN;
            } else {
                // Success Case
                result.put("message", "The fund has been successfully purchased");
            }

        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
