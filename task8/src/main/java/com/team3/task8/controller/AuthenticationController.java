package com.team3.task8.controller;

import com.team3.task8.domain.User;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserRepository userRepository,
                                    AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody Map<String, Object> payload, HttpSession session) {
        System.out.println("username: " + payload.get("username"));
        System.out.println("password: " + payload.get("password"));

        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        return authenticationService.login(session, username, password);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Object> logout(HttpSession session) {
        return authenticationService.logout(session);
    }
}
