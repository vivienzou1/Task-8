package com.team3.task8.controller;

import com.team3.task8.dto.BuyFundForm;
import com.team3.task8.dto.RequestCheckForm;
import com.team3.task8.dto.SellFundForm;
import com.team3.task8.service.BuyFundService;
import com.team3.task8.service.RequestCheckService;
import com.team3.task8.service.SellFundService;
import com.team3.task8.service.ViewPortfolioService;
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
public class CustomerController {

    private final BuyFundService buyFundService;
    private final SellFundService sellFundService;
    private final RequestCheckService requestCheckService;
    private final ViewPortfolioService viewPortfolioService;

    @Autowired
    public CustomerController(BuyFundService buyFundService,
                              SellFundService sellFundService,
                              RequestCheckService requestCheckService,
                              ViewPortfolioService viewPortfolioService) {
        this.buyFundService = buyFundService;
        this.sellFundService = sellFundService;
        this.requestCheckService = requestCheckService;
        this.viewPortfolioService = viewPortfolioService;
    }

    @RequestMapping(value = "/viewPortfolio", method = RequestMethod.GET)
    public ResponseEntity<Object> viewPortfolio(HttpSession session) {

//        System.out.println("/viewPortfolio");

        return viewPortfolioService.viewPortfolio(session);
    }

    @RequestMapping(value = "/buyFund", method = RequestMethod.POST)
    public ResponseEntity<Object> buyFund(@RequestBody @Valid BuyFundForm buyFundForm, HttpSession session) {

//        System.out.println("/buyFund");
//        System.out.println("symbol: " + buyFundForm.getSymbol());
//        System.out.println("cashValue: " + buyFundForm.getCashValue());

        return buyFundService.buyFund(session, buyFundForm);
    }

    @RequestMapping(value = "/sellFund", method = RequestMethod.POST)
    public ResponseEntity<Object> sellFund(@RequestBody @Valid SellFundForm sellFundForm, HttpSession session) {

//        System.out.println("/sellFund");
//        System.out.println("symbol: " + sellFundForm.getSymbol());
//        System.out.println("numShares: " + sellFundForm.getNumShares());

        return sellFundService.sellFund(session, sellFundForm);
    }

    @RequestMapping(value = "/requestCheck", method = RequestMethod.POST)
    public ResponseEntity<Object> requestCheck(@RequestBody @Valid RequestCheckForm requestCheckForm, HttpSession session) {

//        System.out.println("/requestCheck");
//        System.out.println("cashValue: " + requestCheckForm.getCashValue());

        return requestCheckService.requestCheck(session, requestCheckForm);
    }
}
