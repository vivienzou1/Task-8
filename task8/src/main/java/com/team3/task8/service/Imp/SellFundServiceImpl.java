package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import com.team3.task8.dto.SellFundForm;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;


@Service
@Transactional
public class SellFundServiceImpl implements SellFundService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final FundHoldRepository fundHoldRepository;

    @Autowired
    public SellFundServiceImpl(UserRepository userRepository,
                               FundRepository fundRepository,
                               FundHoldRepository fundHoldRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.fundHoldRepository = fundHoldRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> sellFund(HttpSession session, SellFundForm sellFundForm) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        int shares = Integer.parseInt(sellFundForm.getNumShares());

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

                Fund fund = fundRepository.findBySymbol(sellFundForm.getSymbol());

                if (fund == null) {

                    // Fund doesn’t exist
                    result.put("message", "The fund you provided does not exist");
//                    httpStatus = HttpStatus.FORBIDDEN;

                } else {

                    FundHold fundHold = fundHoldRepository.findByUsernameAndName(user.getUsername(), fund.getName());

                    if (fundHold == null || Integer.parseInt(fundHold.getShares()) < shares) {

                        // Not enough shares
                        result.put("message", "You don’t have that many shares in your portfolio");
//                        httpStatus = HttpStatus.FORBIDDEN;

                    } else {

                        // Success Case
                        double prevCash = Double.parseDouble(user.getCash());
                        double price = Double.parseDouble(fund.getPrice());
                        double cash = price * shares;
                        int prevShare = Integer.parseInt(fundHold.getShares());

                        fundHoldRepository.updateSharesById(String.valueOf(prevShare - shares), fund.getId());

                        DecimalFormat df = new DecimalFormat("#.##");
                        userRepository.updateCashByUsername(df.format(prevCash + cash), user.getUsername());

                        result.put("message", "The shares have been successfully sold");

                    }
                }
            }
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
