package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import com.team3.task8.dto.BuyFundForm;
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
import java.text.DecimalFormat;


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
    @Transactional
    public ResponseEntity<Object> buyFund(HttpSession session, BuyFundForm buyFundForm) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (session.getAttribute("username") == null) {

            // Not Logged In
            result.put("message", "You are not currently logged in");
//            httpStatus = HttpStatus.FORBIDDEN;

        } else {

            User user = userRepository.findByUsername((String) session.getAttribute("username"));

            if (!user.getRole().equals("customer")) {

                // Not customer
                result.put("message", "You must be an customer to perform this action");
//                httpStatus = HttpStatus.FORBIDDEN;

            } else {

                double prevCash = Double.parseDouble(user.getCash());
                double cashDouble = Double.parseDouble(buyFundForm.getCashValue());

                Fund fund = fundRepository.findBySymbol(buyFundForm.getSymbol());

                if (fund == null) {

                    // Fund doesn’t exist
                    result.put("message", "The fund you provided does not exist");
//                    httpStatus = HttpStatus.FORBIDDEN;

                } else if (prevCash < cashDouble) {

                    // Not enough cash in account
                    result.put("message", "You don’t have enough cash in your account to make this purchase");
//                    httpStatus = HttpStatus.FORBIDDEN;

                } else if (cashDouble < Double.parseDouble(fund.getPrice())) {

                    // Not enough cash provided
                    result.put("message", "You didn’t provide enough cash to make this purchase");
//                    httpStatus = HttpStatus.FORBIDDEN;

                } else {

                    // Success Case
                    double price = Double.parseDouble(fund.getPrice());
                    int shares = (int) (cashDouble / price);
                    cashDouble = shares * price;

                    FundHold fundHold;
                    if (fundHoldRepository.findByUsernameAndName(user.getUsername(), fund.getName()) == null) {
                        fundHold = new FundHold(fund.getName(), String.valueOf(shares), fund.getPrice(), user.getUsername());
                        fundHoldRepository.save(fundHold);
                    } else {
                        fundHold = fundHoldRepository.findByUsernameAndName(user.getUsername(), fund.getName());
                        double prevShare = Double.parseDouble(fundHold.getShares());
                        fundHoldRepository.updateSharesById(String.valueOf(prevShare + shares), fund.getId());
                    }

                    DecimalFormat df = new DecimalFormat("#.##");
                    userRepository.updateCashByUsername(df.format(prevCash - cashDouble), user.getUsername());
                    result.put("message", "The fund has been successfully purchased");
                }
            }
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
