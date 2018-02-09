//package com.task7.leo.controller;
//
//import com.task7.leo.repositories.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.RESTController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.security.Principal;
//
//
//@RESTController
//public class FunctionController {
//
//
//    final private CustomerRepository customerRepository;
//
//    @Autowired
//    public FunctionController(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//
//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String home(Principal principal) {
//        if (customerRepository.findCustomerByUsername(principal.getName()) == null) {
//            return "redirect:employee-console";
//        }
//
//        return "redirect:customer-console";
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(Principal principal) {
//        if (customerRepository.findCustomerByUsername(principal.getName()) == null) {
//            return "redirect:employee-console";
//        }
//
//        return "redirect:customer-console";
//    }
//
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    @RequestMapping(value = "/employee", method = RequestMethod.GET)
//    public String employee() {
//        return "employee";
//    }
//
//}
