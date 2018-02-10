package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import com.team3.task8.service.TransitionDayService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;


@Service
public class TransitionDayServiceImpl implements TransitionDayService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;

    @Autowired
    public TransitionDayServiceImpl(UserRepository userRepository, FundRepository fundRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
    }

    @Override
    public ResponseEntity<Object> transitionDay(HttpSession session) {
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
            // update price
            result.put("message", "The fund prices have been successfully recalculated");
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
