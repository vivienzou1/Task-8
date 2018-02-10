package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.FundHold;
import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundHoldRepository;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.RequestCheckService;
import com.team3.task8.service.SellFundService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class RequestCheckServiceImpl implements RequestCheckService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final FundHoldRepository fundHoldRepository;

    @Autowired
    public RequestCheckServiceImpl(UserRepository userRepository, FundRepository fundRepository, FundHoldRepository fundHoldRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.fundHoldRepository = fundHoldRepository;
    }

    @Override
    public ResponseEntity<Object> requestCheck(HttpSession session, String cash) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // Parameter invalid ???
//        httpStatus = HttpStatus.BAD_REQUEST;

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
            // deposit
            result.put("message", "The check was successfully deposited");
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
