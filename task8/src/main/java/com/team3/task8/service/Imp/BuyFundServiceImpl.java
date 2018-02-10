package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundHoldRepository;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;


@Service
@Transactional
public class BuyFundServiceImpl implements BuyFundService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final FundHoldRepository fundHoldRepository;

    @Autowired
    public BuyFundServiceImpl(UserRepository userRepository,
                              FundRepository fundRepository,
                              FundHoldRepository fundHoldRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.fundHoldRepository = fundHoldRepository;
    }

    @Override
    public ResponseEntity<Object> buyFund(HttpSession session, String symbol, String cashValue) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // Parameter invalid
        double cashDouble = 0;
        try {
            cashDouble = Double.parseDouble(cashValue);
        } catch (NumberFormatException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(result, httpStatus);
        }

        if (session.getAttribute("username") == null) {
            // Not Logged In
            result.put("message", "You are not currently logged in");
            httpStatus = HttpStatus.FORBIDDEN;
        } else {
            User user = userRepository.findByUsername((String) session.getAttribute("username"));
            if (!user.getRole().equals("customer")) {
                // Not customer
                result.put("message", "You must be an customer to perform this action");
                httpStatus = HttpStatus.FORBIDDEN;
            } else if (user.getCash() < cashDouble) {
                // Not enough cash in account
                result.put("message", "You don’t have enough cash in your account to make this purchase");
                httpStatus = HttpStatus.FORBIDDEN;
            } else {
                // Success Case
                double prevCash = user.getCash();
                Fund fund = fundRepository.findBySymbol(symbol);
                double price = fund.getPrice();
                double shares = cashDouble / price;
                FundHold fundHold;
                if (fundHoldRepository.findByUsernameAndName(user.getUsername(), fund.getName()) == null) {
                    fundHold = new FundHold(fund.getName(), shares, price, user.getUsername());
                    fundHoldRepository.save(fundHold);
                } else {
                    fundHold = fundHoldRepository.findByUsernameAndName(user.getUsername(), fund.getName());
                    double prevShare = fundHold.getShares();
                    fundHoldRepository.updateSharesById(prevShare + shares, fund.getId());
                }
                userRepository.updateCashByUsername(prevCash - cashDouble, user.getUsername());
                result.put("message", "The fund has been successfully purchased");
            }

        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
