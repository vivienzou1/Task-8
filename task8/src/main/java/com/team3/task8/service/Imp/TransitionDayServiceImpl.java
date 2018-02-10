package com.team3.task8.service.Imp;


import com.team3.task8.domain.Fund;
import com.team3.task8.domain.User;
import com.team3.task8.repositories.FundRepository;
import com.team3.task8.repositories.UserRepository;
import com.team3.task8.service.BuyFundService;
import com.team3.task8.service.TransitionDayService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class TransitionDayServiceImpl implements TransitionDayService {

    private final UserRepository userRepository;
    private final FundRepository fundRepository;

    @Autowired
    public TransitionDayServiceImpl(UserRepository userRepository, FundRepository fundRepository) {
        this.userRepository = userRepository;
        this.fundRepository = fundRepository;
    }

    @Override
    public ResponseEntity<Object> transitionDay(HttpSession session) {
        JSONObject result = new JSONObject();
        HttpStatus httpStatus = HttpStatus.OK;

        if (session.getAttribute("username") == null) {

            // Not Logged In
            result.put("message", "You are not currently logged in");
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (!userRepository.findByUsername((String) session.getAttribute("username")).getRole().equals("Employee")) {

            // Not employee
            result.put("message", "You must be an employee to perform this action");
            httpStatus = HttpStatus.FORBIDDEN;
        } else {

            // Success Case
            List<Fund> funds = fundRepository.findAll();
            for (Fund fund : funds) {
                double curPrice = Double.parseDouble(fund.getPrice());
                DecimalFormat df = new DecimalFormat("#.##");
                String newPrice = df.format(newPrice(curPrice));
                fundRepository.updatePriceBySymbol(newPrice, fund.getSymbol());
            }
            result.put("message", "The fund prices have been successfully recalculated");
        }

        return new ResponseEntity<>(result, httpStatus);
    }

    private double newPrice(double price) {
        double newPrice;
        double fluctuate;
        while (true) {
            fluctuate = (int) (Math.random() * (price * 20) - price * 10) / 100;
            newPrice = price + fluctuate;
            if (newPrice > 0) {
                break;
            }
        }
        System.out.println("prev price: " + price);
        System.out.println("fluctuate: " + fluctuate);
        System.out.println("new price: " + newPrice);
        return newPrice;
    }
}
