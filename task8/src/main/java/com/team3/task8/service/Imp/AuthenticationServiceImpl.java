package com.team3.task8.service.Imp;


import com.team3.task8.domain.User;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.AuthenticationService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;


@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> login(HttpSession session, String username, String password) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        // param check ???

        User user = userRepository.findByUsername(username);

        if (user == null || !password.equals(user.getPassword())) {
            // Failure case
            result.put("message", "There seems to be an issue with the username/password combination that you entered");
            httpStatus = httpStatus.FORBIDDEN;
        } else {
            // Success Case
            session.setAttribute("username", username);
            result.put("message", "Welcome " + user.getFname());
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    @Override
    public ResponseEntity<Object> logout(HttpSession session) {

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (session.getAttribute("username") == null) {
            // Failure case
            result.put("message", "You are not currently logged in");
            httpStatus = httpStatus.FORBIDDEN;
        } else {
            // Success Case
            session.setAttribute("username", null);
            result.put("message", "You have been successfully logged out");
        }

        return new ResponseEntity<>(result, httpStatus);
    }
}
