package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundHoldRepository;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import com.team3.task8.service.SellFundService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class SellFundServiceImpl implements SellFundService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final FundHoldRepository fundHoldRepository;

    @Autowired
    public SellFundServiceImpl(UserRepository userRepository, FundRepository fundRepository, FundHoldRepository fundHoldRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.fundHoldRepository = fundHoldRepository;
    }

    @Override
    public ResponseEntity<Object> sellFund(HttpSession session, String symbol, String numShares) {
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
            } else if (fundRepository.findBySymbol(symbol) == null) {
                // Fund doesn’t exist
                result.put("message", "The fund you provided does not exist");
                httpStatus = HttpStatus.FORBIDDEN;
            } else {
                Fund fund = fundRepository.findBySymbol(symbol);
                FundHold fundHold = fundHoldRepository.findByName(fund.getName());
                FundHold userFundHold = userRepository.findByUsernameAndFundHoldsEquals(user.getUsername(), fundHold);
                if (userFundHold == null || userFundHold.getShares() < Double.parseDouble(numShares)) {
                    // Not enough shares
                    result.put("message", "You don’t have that many shares in your portfolio");
                    httpStatus = HttpStatus.FORBIDDEN;
                } else {
                    // Success Case
                    // sell fund
                    result.put("message", "The fund has been successfully purchased");
                }
            }
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}