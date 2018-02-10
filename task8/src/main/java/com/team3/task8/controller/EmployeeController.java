package com.team3.task8.controller;

import com.team3.task8.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class EmployeeController {

    private final CreateCustomerService createCustomerService;
    private final DepositCheckService depositCheckService;
    private final CreateFundService createFundService;
    private final TransitionDayService transitionDayService;

    @Autowired
    public EmployeeController(CreateCustomerService createCustomerService,
                              DepositCheckService depositCheckService,
                              CreateFundService createFundService,
                              TransitionDayService transitionDayService) {
        this.createCustomerService = createCustomerService;
        this.depositCheckService = depositCheckService;
        this.createFundService = createFundService;
        this.transitionDayService = transitionDayService;
    }

    @RequestMapping(value = "/createCustomerAccount", method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomerAccount(@RequestBody Map<String, Object> payload, HttpSession session) {

        System.out.println("fname: " + payload.get("fname"));
        System.out.println("lname: " + payload.get("lname"));
        System.out.println("address: " + payload.get("address"));
        System.out.println("city: " + payload.get("city"));
        System.out.println("state: " + payload.get("state"));
        System.out.println("zip: " + payload.get("zip"));
        System.out.println("email: " + payload.get("email"));
        try {
            System.out.println("cash: " + payload.get("cash"));
        } catch (Exception e) {
            System.out.println("No cash provided");
        }
        System.out.println("username: " + payload.get("username"));
        System.out.println("password: " + payload.get("password"));

        String fname = (String) payload.get("fname");
        String lname = (String) payload.get("lname");
        String address = (String) payload.get("address");
        String city = (String) payload.get("city");
        String state = (String) payload.get("state");
        String zip = (String) payload.get("zip");
        String email = (String) payload.get("email");
        String cash;
        try {
            cash = (String) payload.get("cash");
        } catch (Exception e) {
            cash = "0";
        }
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        return createCustomerService.createCustomer(session, fname, lname, address, city, state, zip, email, cash, username, password);
    }

    @RequestMapping(value = "/depositCheck", method = RequestMethod.POST)
    public ResponseEntity<Object> depositCheck(@RequestBody Map<String, Object> payload, HttpSession session) {

        System.out.println("username: " + payload.get("username"));
        System.out.println("cash: " + payload.get("cash"));

        String username = (String) payload.get("username");
        String cash = (String) payload.get("cash");

        return depositCheckService.depositCheck(session, username, cash);
    }

    @RequestMapping(value = "/createFund", method = RequestMethod.POST)
    public ResponseEntity<Object> createFund(@RequestBody Map<String, Object> payload, HttpSession session) {

        System.out.println("name: " + payload.get("name"));
        System.out.println("symbol: " + payload.get("symbol"));
        System.out.println("initial_value: " + payload.get("initial_value"));

        String name = (String) payload.get("name");
        String symbol = (String) payload.get("symbol");
        String initial_value = (String) payload.get("initial_value");

        return createFundService.createFund(session, name, symbol, initial_value);
    }

    @RequestMapping(value = "/transitionDay", method = RequestMethod.POST)
    public ResponseEntity<Object> transitionDay(HttpSession session) {

        return transitionDayService.transitionDay(session);
    }
}
