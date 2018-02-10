package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundHoldRepository;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.SellFundService;
import com.team3.task8.service.ViewPortfolioService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Service
public class ViewPortfolioServiceImpl implements ViewPortfolioService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final FundHoldRepository fundHoldRepository;

    @Autowired
    public ViewPortfolioServiceImpl(UserRepository userRepository, FundRepository fundRepository, FundHoldRepository fundHoldRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.fundHoldRepository = fundHoldRepository;
    }

    @Override
    public ResponseEntity<Object> viewPortfolio(HttpSession session) {
        JSONObject result = new JSONObject();
        List<JSONObject> funds = new ArrayList<JSONObject>();
        HttpStatus httpStatus = HttpStatus.OK;

        // Parameter invalid ???
//        httpStatus = HttpStatus.BAD_REQUEST;

        if (session.getAttribute("username") == null) {
            // Not Logged In
            result.put("message", "You are not currently logged in");
            httpStatus = HttpStatus.FORBIDDEN;
        } else {
            // No Funds
            User user = userRepository.findByUsername((String) session.getAttribute("username"));

            if (!user.getRole().equals("customer")) {
                // Not customer
                result.put("message", "You must be a customer to perform this action");
                httpStatus = HttpStatus.FORBIDDEN;
            } else if (user.getFundHolds() == null && user.getFundHolds().size() == 0) {
                result.put("message", "You don’t have any funds in your Portfolio");
                httpStatus = HttpStatus.FORBIDDEN;
            } else {
                // Success Case
                result.put("message", "The fund has been successfully purchased");
                result.put("cash", "current balance in account");
                for (FundHold fundhold : user.getFundHolds()) {
                    JSONObject fund = new JSONObject();
                    fund.put("name", fundhold.getName());
                    fund.put("shares", fundhold.getShares());
                    fund.put("price", fundhold.getPrice());
                    funds.add(fund);
                }
                result.put("funds", funds);
            }
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
