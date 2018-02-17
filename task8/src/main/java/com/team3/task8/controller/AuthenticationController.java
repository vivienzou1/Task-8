package com.team3.task8.controller;

import com.team3.task8.dto.LoginForm;
import com.team3.task8.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody @Valid LoginForm loginForm, HttpSession session) {

//        System.out.println("/login");
//        System.out.println("username: " + loginForm.getUsername());
//        System.out.println("password: " + loginForm.getPassword());

        return authenticationService.login(session, loginForm);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Object> logout(HttpSession session) {

//        System.out.println("/logout");
        return authenticationService.logout(session);
    }
}
