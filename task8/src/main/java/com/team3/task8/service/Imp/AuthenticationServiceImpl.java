package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.dto.LoginForm;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.AuthenticationService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;


@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> login(HttpSession session, LoginForm loginForm) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        User user = userRepository.findByUsername(loginForm.getUsername());

        if (user == null || !loginForm.getPassword().equals(user.getPassword())) {

            // Failure case
            result.put("message", "There seems to be an issue with the username/password combination that you entered");
//            httpStatus = httpStatus.FORBIDDEN;

        } else {

            // Success Case
            session.setAttribute("username", loginForm.getUsername());
            result.put("message", "Welcome " + user.getFname());

        }
        System.out.println("this is login");
        System.out.println("message is : " + result.toString());
        System.out.println("httpStatus is : " + httpStatus.toString());
        return new ResponseEntity<>(result, httpStatus);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> logout(HttpSession session) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (session.getAttribute("username") == null) {

            // Failure case
            result.put("message", "You are not currently logged in");
//            httpStatus = httpStatus.FORBIDDEN;

        } else {

            // Success Case
            session.setAttribute("username", null);
            result.put("message", "You have been successfully logged out");

        }

        System.err.println("this is logout");
        System.err.println("message is : " + result.toString());
        System.err.println("httpStatus is : " + httpStatus.toString());
        return new ResponseEntity<>(result, httpStatus);
    }
}
