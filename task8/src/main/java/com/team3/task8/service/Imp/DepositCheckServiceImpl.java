package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.dto.DepositCheckForm;
import com.team3.task8.repositories.FundHoldRepository;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.DepositCheckService;
import com.team3.task8.service.RequestCheckService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;


@Service
public class DepositCheckServiceImpl implements DepositCheckService {

    private final UserRepository userRepository;

    @Autowired
    public DepositCheckServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> depositCheck(HttpSession session, DepositCheckForm depositCheckForm) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (session.getAttribute("username") == null) {

            // Not Logged In
            result.put("message", "You are not currently logged in");
//            httpStatus = HttpStatus.FORBIDDEN;

        } else if (!userRepository.findByUsername((String) session.getAttribute("username")).getRole().equals("Employee")) {

            // Not employee
            result.put("message", "You must be an employee to perform this action");
//            httpStatus = HttpStatus.FORBIDDEN;

        } else {

            // Success Case
            User user = userRepository.findByUsername(depositCheckForm.getUsername());

            double prevCash = Double.parseDouble(user.getCash());
            double cashDouble = Double.parseDouble(depositCheckForm.getCash());

            DecimalFormat df = new DecimalFormat("#.##");
            String newCash = df.format(prevCash + cashDouble);

            userRepository.updateCashByUsername(newCash, depositCheckForm.getUsername());
            result.put("message", "The check was successfully deposited");

        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
