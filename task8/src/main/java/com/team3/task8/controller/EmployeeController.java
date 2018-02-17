package com.team3.task8.controller;

import com.team3.task8.dto.CreateCustomerForm;
import com.team3.task8.dto.CreateFundForm;
import com.team3.task8.dto.DepositCheckForm;
import com.team3.task8.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public ResponseEntity<Object> createCustomerAccount(@RequestBody @Valid CreateCustomerForm createCustomerForm, HttpSession session) {



//        System.out.println("/createCustomerAccount");
//        System.out.println("fname: " + createCustomerForm.getFname());
//        System.out.println("lname: " + createCustomerForm.getLname());
//        System.out.println("address: " + createCustomerForm.getAddress());
//        System.out.println("city: " + createCustomerForm.getCity());
//        System.out.println("state: " + createCustomerForm.getState());
//        System.out.println("zip: " + createCustomerForm.getZip());
//        System.out.println("email: " + createCustomerForm.getEmail());
//        try {
//            System.out.println("cash: " + createCustomerForm.getCash());
//        } catch (Exception e) {
//            System.out.println("No cash provided");
//        }
//        System.out.println("username: " + createCustomerForm.getUsername());
//        System.out.println("password: " + createCustomerForm.getPassword());

        return createCustomerService.createCustomer(session, createCustomerForm);
    }

    @RequestMapping(value = "/depositCheck", method = RequestMethod.POST)
    public ResponseEntity<Object> depositCheck(@RequestBody @Valid DepositCheckForm depositCheckForm, HttpSession session) {

        //System.out.println("/depositCheck");
        //System.out.println("username: " + depositCheckForm.getUsername());
        //System.out.println("cash: " + depositCheckForm.getCash());

        return depositCheckService.depositCheck(session, depositCheckForm);
    }

    @RequestMapping(value = "/createFund", method = RequestMethod.POST)
    public ResponseEntity<Object> createFund(@RequestBody @Valid CreateFundForm createFundForm, HttpSession session) {

        //System.out.println("/createFund");
        //System.out.println("name: " + createFundForm.getName());
        //System.out.println("symbol: " + createFundForm.getSymbol());
        //System.out.println("initial_value: " + createFundForm.getInitial_value());

        return createFundService.createFund(session, createFundForm);
    }

    @RequestMapping(value = "/transitionDay", method = RequestMethod.POST)
    public ResponseEntity<Object> transitionDay(HttpSession session) {

//        System.out.println("/transitionDay");

        return transitionDayService.transitionDay(session);
    }
}
