package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.dto.CreateCustomerForm;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import com.team3.task8.service.CreateCustomerService;
import com.team3.task8.util.ParamCheck;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;


@Service
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;
    private final ParamCheck paramCheck;

    @Autowired
    public CreateCustomerServiceImpl(UserRepository userRepository,
                                     FundRepository fundRepository,
                                     ParamCheck paramCheck) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
        this.paramCheck = paramCheck;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> createCustomer(HttpSession session, CreateCustomerForm createCustomerForm) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (session.getAttribute("username") == null) {

            // Not Logged In
            result.put("message", "You are not currently logged in");
            httpStatus = HttpStatus.FORBIDDEN;

        } else if (!userRepository.findByUsername((String) session.getAttribute("username")).getRole().equals("Employee")) {

            // Not employee
            result.put("message", "You must be an employee to perform this action");
            httpStatus = HttpStatus.FORBIDDEN;

        } else if (userRepository.findByUsername(createCustomerForm.getUsername()) != null) {

            // Duplicate username
            result.put("message", "The input you provided is not valid");
            httpStatus = HttpStatus.FORBIDDEN;

        } else {

            String cash = "0";
            if (createCustomerForm.getCash() != null) {
                cash = createCustomerForm.getCash();
            }

            // Success Case
            User user = new User(
                    createCustomerForm.getFname(),
                    createCustomerForm.getLname(),
                    createCustomerForm.getAddress(),
                    createCustomerForm.getCity(),
                    createCustomerForm.getState(),
                    createCustomerForm.getZip(),
                    createCustomerForm.getEmail(),
                    cash,
                    "customer",
                    createCustomerForm.getUsername(),
                    createCustomerForm.getPassword()
            );

            userRepository.save(user);
            result.put("message", createCustomerForm.getFname() + " was registered successfully");

        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
