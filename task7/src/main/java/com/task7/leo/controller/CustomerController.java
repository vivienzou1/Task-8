package com.task7.leo.controller;


import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Fund;
import com.task7.leo.domain.FundHold;
import com.task7.leo.domain.Transaction;
import com.task7.leo.dto.*;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.FundHoldRepository;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.repositories.TransactionRepository;
import com.task7.leo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
//@PreAuthorize(value = "hasRole('CUSTOMER')")
public class CustomerController {

    private final FundRepository fundRepository;
    private final BuyFundService buyFundService;
    private final SellFundService sellFundService;
    private final WithdrawService withdrawService;
    private final GetCustomerService getCustomerService;
    private final CustomerPasswordService customerPasswordService;
    private final FundHoldRepository fundHoldRepository;
    private final TransactionRepository transactionRepository;
    private SimpleDateFormat sdf;

    @Autowired
    public CustomerController(FundRepository fundRepository,
                              BuyFundService buyFundService,
                              SellFundService sellFundService,
                              WithdrawService withdrawService,
                              GetCustomerService getCustomerService,
                              CustomerPasswordService customerPasswordService,
                              FundHoldRepository fundHoldRepository,
                              TransactionRepository transactionRepository) {
        this.fundRepository = fundRepository;
        this.buyFundService = buyFundService;
        this.sellFundService = sellFundService;
        this.withdrawService = withdrawService;
        this.getCustomerService = getCustomerService;
        this.customerPasswordService = customerPasswordService;
        this.fundHoldRepository = fundHoldRepository;
        this.transactionRepository = transactionRepository;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("haha");
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

    @ModelAttribute("customer")
    public Customer currentCustomer(Principal principal) {
        return getCustomerService.currentCustomer(principal.getName());
    }

    @ModelAttribute("customerPasswordForm")
    public CustomerPasswordForm customerPasswordForm() {
        return new CustomerPasswordForm();
    }

    @ModelAttribute("fundHolds")
    public List<FundHold> fundHolds(Principal principal){
        return fundHoldRepository.findFundHoldsByCustomer_Username(principal.getName());
    }

    @ModelAttribute("buyForm")
    public BuyForm buyForm() {
        return new BuyForm();
    }

    @ModelAttribute("sellForm")
    public SellForm sellForm() {
        return new SellForm();
    }

    @ModelAttribute("withdrawForm")
    public WithdrawForm withdrawForm() {
        return new WithdrawForm();
    }

    @ModelAttribute("searchFundForm")
    public SearchFundForm searchFundForm() {
        return new SearchFundForm();
    }

    @ModelAttribute("funds")
    public List<Fund> funds() {
        return fundRepository.findAll();
    }

    @ModelAttribute("mytransactions")
    public List<Transaction> myTransactions(Principal principal) {
        return transactionRepository.findByCustomer_Username(principal.getName());
    }

    @ModelAttribute("fund")
    public Fund fund() {
        return new Fund();
    }

    @RequestMapping(value = "/customer-console")
    public String console() {
        return "customer";
    }

    @RequestMapping(value = "/fundholds", method = RequestMethod.GET)
    public String fundholds() {
        return "customer";
    }

    @RequestMapping(value = "/buyfund", method = RequestMethod.GET)
    public String buyFundForm() {
        return "customer";
    }

    @RequestMapping(value = "/sellfund", method = RequestMethod.GET)
    public String sellFundForm() {
        return "customer";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdrawFundForm() {
        return "customer";
    }

    @RequestMapping(value = "/fundsfunds")
    public String fundList()
    {
        return "customer";
    }

    @RequestMapping(value = "/myprofile")
    public String customerProfile(Model model, Principal principal) {
        return "customer";
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.GET)
    public String customerPassowrdForm() {
        return "customer";
    }

    @Transactional
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String customerPassowrdReset(Principal principal, @Valid CustomerPasswordForm customerPasswordForm, BindingResult result) {
        if (result.hasErrors()) {
            return "customer";
        }
        customerPasswordService.resetPasswordByUsername(customerPasswordForm, principal.getName());
        return "redirect:/success";
    }

    @RequestMapping(value = "/buyfund", method = RequestMethod.POST)
    public String buyFund(Principal principal, @ModelAttribute(value = "buyForm") @Valid BuyForm buyForm,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "customer";
        }
        try {
            buyFundService.buyFund(buyForm, principal.getName());
        } catch (RuntimeException e) {
            result.reject(e.getMessage());
            return "customer";
        }

        return "redirect:/success";
    }

    @RequestMapping(value = "/sellfund", method = RequestMethod.POST)
    public String sellFund(Principal principal, @ModelAttribute(value = "sellForm") @Valid SellForm sellForm,
                          BindingResult result) {

        if (result.hasErrors()) {
            return "customer";
        }
        try {
            sellFundService.sellFund(sellForm, principal.getName());
        } catch (RuntimeException e) {
            result.reject(e.getMessage());
            return "customer";
        }

        return "redirect:/success";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawFund(Principal principal, @ModelAttribute(value = "withdrawForm") @Valid WithdrawForm withdrawForm,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "customer";
        }
        try {
            withdrawService.withdraw(withdrawForm, principal.getName());
        } catch (RuntimeException e) {
            result.reject(e.getMessage());
            return "customer";
        }

        return "redirect:/success";
    }

    @RequestMapping(value = "/fund-detail", params = {"id"}, method = RequestMethod.GET)
    public String fundDetail(Model model, @RequestParam(value = "id") long id) {
        Fund fund = fundRepository.findById(id);
        model.addAttribute("fund", fund);
        return "customer";
    }

//    @RequestMapping(value = "/funds")
//    public String fundlist() {
//        return "customer";
//    }
    @RequestMapping(value = "/funds")
    public List<Fund> fundlist() {
        System.out.println("funds");
        return fundRepository.findAll();
    }

    @RequestMapping(value = "/mytransaction")
    public String transactionlist() {
        return "customer";
    }

    @RequestMapping(value = "/success")
    public String success() {
        return "customer";
    }


}
