package com.team3.task8.controller;

import com.team3.task8.domain.User;
import com.team3.task8.repositories.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AuthenticationController {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody Map<String, Object> payload, HttpSession session) {
        System.out.println("username: " + payload.get("username"));
        System.out.println("password: " + payload.get("password"));

        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        User user = userRepository.findByUsername(username);

        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (user == null || !password.equals(user.getPassword())) {
            // Failure case
            result.put("message", "There seems to be an issue with the username/password combination that you entered");
            httpStatus = httpStatus.FORBIDDEN;
        } else {
            // Success Case
            session.setAttribute("username", username);
            result.put("message", "Welcome first name");
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Object> logout(HttpSession session) {
        boolean success = true;

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
