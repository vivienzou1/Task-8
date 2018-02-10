package com.team3.task8.controller;

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

        return viewPortfolioService.viewPortfolio(session);
    }

    @RequestMapping(value = "/buyFund", method = RequestMethod.POST)
    public ResponseEntity<Object> buyFund(@RequestBody Map<String, Object> payload, HttpSession session) {

        System.out.println("symbol: " + payload.get("symbol"));
        System.out.println("cashValue: " + payload.get("cashValue"));

        String symbol = (String) payload.get("symbol");
        String cashValue = (String) payload.get("cashValue");

        return buyFundService.buyFund(session, symbol, cashValue);
    }

    @RequestMapping(value = "/sellFund", method = RequestMethod.POST)
    public ResponseEntity<Object> sellFund(@RequestBody Map<String, Object> payload, HttpSession session) {

        System.out.println("symbol: " + payload.get("symbol"));
        System.out.println("numShares: " + payload.get("numShares"));

        String symbol = (String) payload.get("symbol");
        String numShares = (String) payload.get("numShares");

        return sellFundService.sellFund(session, symbol, numShares);
    }


    @RequestMapping(value = "/requestCheck", method = RequestMethod.POST)
    public ResponseEntity<Object> requestCheck(@RequestBody Map<String, Object> payload, HttpSession session) {

        System.out.println("cashValue: " + payload.get("cashValue"));

        String cashValue = (String) payload.get("cashValue");

        return requestCheckService.requestCheck(session, cashValue);
    }
}
