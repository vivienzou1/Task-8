package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundHoldRepository;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.DepositCheckService;
import com.team3.task8.service.RequestCheckService;
import com.team3.task8.util.ParamCheck;
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
public class DepositCheckServiceImpl implements DepositCheckService {

    private final UserRepository userRepository;
    private final ParamCheck paramCheck;

    @Autowired
    public DepositCheckServiceImpl(UserRepository userRepository,
                                   ParamCheck paramCheck) {
        this.userRepository = userRepository;
        this.paramCheck = paramCheck;
    }

    @Override
    public ResponseEntity<Object> depositCheck(HttpSession session, String username, String cash) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // param check ???

        // cash not double or more than two decimals
        if (!paramCheck.isTwoDecimal(cash)) {
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

        } else {

            // Success Case
            User user = userRepository.findByUsername(username);
            double prevCash = Double.parseDouble(user.getCash());
            double cashDouble = Double.parseDouble(cash);

            DecimalFormat df = new DecimalFormat("#.##");
            String newCash = df.format(prevCash + cashDouble);

            userRepository.updateCashByUsername(newCash, username);
            result.put("message", "The check was successfully deposited");

        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
