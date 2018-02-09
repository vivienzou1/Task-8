package com.task7.leo.controller;
import com.task7.leo.domain.*;
import com.task7.leo.dto.*;
import com.task7.leo.repositories.*;
import com.task7.leo.service.*;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;
import java.text.SimpleDateFormat;


@Controller
//@PreAuthorize(value = "hasRole('EMPLOYEE')")
public class EmployeeController {

    private final FundRepository fundRepository;

    private final TransitionDayService transitionDayService;
    private final LastTransitionIdRepository lastTransitionIdRepository;
    private final DepositService depositService;
    private final CreateFundService createFundService;
    private final RegisterService registerService;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeePasswordService employeePasswordService;
    private final CustomerPasswordService customerPasswordService;
    private final TransactionRepository transactionRepository;
    private final GetEmployeeService getEmployeeService;
    private final GetCustomerService getCustomerService;
    private final FundHoldRepository fundHoldRepository;

    private SimpleDateFormat sdf;

    @Autowired
    public EmployeeController(TransitionDayService transitionDayService,
                              FundRepository fundRepository,
                              LastTransitionIdRepository lastTransitionIdRepository,
                              DepositService depositService,
                              CreateFundService createFundService,
                              RegisterService registerService,
                              CustomerRepository customerRepository,
                              EmployeeRepository employeeRepository,
                              EmployeePasswordService employeePasswordService,
                              CustomerPasswordService customerPasswordService,
                              TransactionRepository transactionRepository,
                              GetEmployeeService getEmployeeService,
                              GetCustomerService getCustomerService,
                              FundHoldRepository fundHoldRepository) {
        this.transitionDayService = transitionDayService;
        this.fundRepository = fundRepository;
        this.lastTransitionIdRepository = lastTransitionIdRepository;
        this.depositService = depositService;
        this.createFundService = createFundService;
        this.registerService = registerService;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.employeePasswordService = employeePasswordService;
        this.customerPasswordService = customerPasswordService;
        this.transactionRepository = transactionRepository;
        this.getEmployeeService = getEmployeeService;
        this.getCustomerService = getCustomerService;
        this.fundHoldRepository = fundHoldRepository;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    @ModelAttribute("employee")
    public Employee currentEmployee(Principal principal) {
        Employee employee = employeeRepository.findEmployeeByUsername(principal.getName());
        return employee;
    }

    @ModelAttribute("customerRegisterForm")
    public CustomerRegisterForm customerRegisterForm() {
        return new CustomerRegisterForm();
    }

    @ModelAttribute("employeeRegisterForm")
    public EmployeeRegisterForm employeeRegisterForm() {
        return new EmployeeRegisterForm();
    }

    @ModelAttribute("employeePasswordForm")
    public EmployeePasswordForm employeePasswordForm() {
        return new EmployeePasswordForm();
    }

    @ModelAttribute("customerPasswordForm")
    public CustomerPasswordForm customerPasswordForm() {
        return new CustomerPasswordForm();
    }

    @ModelAttribute("depositForm")
    public DepositForm depositForm() {
        return new DepositForm();
    }

    @ModelAttribute("fundCreateForm")
    public FundCreateForm fundCreateForm() {
        return new FundCreateForm();
    }

    @ModelAttribute("fundHolds")
    public List<FundHold> fundHolds(){
        return new ArrayList<FundHold>();
    }

    @ModelAttribute("funds")
    public List<Fund> funds() {
        return fundRepository.findAll();
    }

    @ModelAttribute("customers")
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    @ModelAttribute("transactions")
    public List<Transaction> transactionList() {
        return transactionRepository.findAll();
    }

    @ModelAttribute("customer")
    public Customer customerDetail() {
        return new Customer();
    }

    @RequestMapping(value = "/customers")
    public String customers() {
        return "employee";
    }

    @RequestMapping(value = "/profiles", params = {"id"}, method = RequestMethod.GET)
    public String customerProfile(Model model, @RequestParam(value = "id") long id) {
        Customer customer = getCustomerService.customerById(id);
        if (customer == null) {
            return "404";
        }
        List<FundHold> fundHolds = fundHoldRepository.findFundHoldsByCustomer_Username(customer.getUsername());
        model.addAttribute("customer", customer);
        model.addAttribute("fundHolds", fundHolds);
        return "employee";
    }

    @RequestMapping(value = "/employee-password", method = RequestMethod.GET)
    public String employeePassowrdForm() {
        return "employee";
    }

    @Transactional
    @RequestMapping(value = "/employee-password", method = RequestMethod.POST)
    public String employeePassowrdReset(Principal principal, @Valid EmployeePasswordForm employeePasswordForm, BindingResult result) {
        if (result.hasErrors()) {
            return "employee";
        }
        Employee employee = getEmployeeService.currentEmployee(principal.getName());
        employeePasswordService.resetPassword(employeePasswordForm, employee.getId());
        return "redirect:/success-employee";
    }

    @RequestMapping(value = "/customer-password", params = {"id"}, method = RequestMethod.GET)
    public String customerPassowrdForm(Model model, @RequestParam(value = "id") long id) {
        Customer customer = getCustomerService.customerById(id);
        model.addAttribute("customer", customer);
        return "employee";
    }

    @Transactional
    @RequestMapping(value = "/customer-password", params = {"id"}, method = RequestMethod.POST)
    public String customerPassowrdReset(@Valid CustomerPasswordForm customerPasswordForm, BindingResult result, @RequestParam(value = "id") long id) {
        if (result.hasErrors()) {
            return "employee";
        }
        customerPasswordService.resetPassword(customerPasswordForm, id);
        return "redirect:/success-employee";
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String fundList() {
        return "employee";
    }

    @RequestMapping(value = "/register-customer", method = RequestMethod.GET)
    public String Customer() {
        return "employee";
    }

    @RequestMapping(value = "/register-customer", method = RequestMethod.POST)
    public String registerCustomer(@ModelAttribute(value = "customerRegisterForm") @Valid CustomerRegisterForm customerRegisterForm,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "employee";
        }

        registerService.Register(customerRegisterForm);

        return "redirect:/success-employee";
    }

    @RequestMapping(value = "/register-employee", method = RequestMethod.GET)
    public String registerEmployee() {
        return "employee";
    }

    @RequestMapping(value = "/register-employee", method = RequestMethod.POST)
    public String registerEmployee(@ModelAttribute(value = "employeeRegisterForm") @Valid EmployeeRegisterForm employeeRegisterForm,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "employee";
        }

        registerService.Register(employeeRegisterForm);

        return "redirect:/success-employee";
    }

    @RequestMapping(value = "/employee-console")
    public String console() {
        return "employee";
    }

    @RequestMapping(value = "/deposit")
    public String depositGet() {
        return "employee";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String buyFund(@ModelAttribute(value = "depositForm") @Valid DepositForm depositForm,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "employee";
        }

        try {
            depositService.deposit(depositForm);
        } catch (RuntimeException e) {
            result.reject(e.getMessage());
            return "employee";
        }

        return "redirect:/success-employee";
    }

    @RequestMapping(value = "/newfund", method = RequestMethod.GET)
    public String newFund() {
        return "employee";
    }

    @RequestMapping(value = "/newfund", method = RequestMethod.POST)
    public String createFund(@ModelAttribute(value = "fundCreateForm") @Valid FundCreateForm fundCreateForm,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "employee";
        }

        createFundService.createFund(fundCreateForm);

        return "redirect:/success-employee";
    }


    @ModelAttribute("transitionDayForm")
    public TransitionDayForm transitionDayForm() {
        return transitionDayService.getForm();
    }

    @ModelAttribute("availableDate")
    public String availabelDate() {
        Fund fund = fundRepository.findById(1);
        if (fund != null && fund.getLastTransition() != null) {
            Date date = fund.getLastTransition();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(calendar.DATE, 1);
            return sdf.format(calendar.getTime());
        } else {
            return "0000-00-00";
        }
    }

    @ModelAttribute("lastTransitionDay")
    public String lastDate() {
        Fund fund = fundRepository.findById(1);
        if (fund != null && fund.getLastTransition() != null) {
            return sdf.format(fund.getLastTransition());
        } else {
            return "0000-00-00";
        }
    }

    @RequestMapping(value = "/transitionday", method = RequestMethod.GET)
    public String transitiondayForm() {
        return "employee";
    }

    @RequestMapping(value = "/transitionday", method = RequestMethod.POST)
    public String transitionday(@ModelAttribute(value = "transitionDayForm") @Valid TransitionDayForm transitionDayForm,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "employee";
        }

        Map<String, Double> map =  transitionDayService.updatePrice(transitionDayForm);

        transitionDayService.transitionDay(map);
        transitionDayService.updateLastId();
        return "redirect:/success-employee";
    }

    @RequestMapping(value = "/success-employee")
    public String success() {
        return "employee";
    }


}
