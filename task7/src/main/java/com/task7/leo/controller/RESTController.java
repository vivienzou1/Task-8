package com.task7.leo.controller;


import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Fund;
import com.task7.leo.domain.FundHold;
import com.task7.leo.domain.Transaction;
import com.task7.leo.dto.*;
import com.task7.leo.repositories.FundHoldRepository;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.repositories.TransactionRepository;
import com.task7.leo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RESTController {

    @Autowired
    public RESTController() {
    }


    @RequestMapping(value = "/haha")
    public String haha() {
        return "haha";
    }
}
