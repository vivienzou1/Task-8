package com.task7.leo.service;


import com.task7.leo.domain.Transaction;
import com.task7.leo.dto.TransitionDayForm;

import java.util.Date;
import java.util.Map;

public interface TransitionDayService {

    TransitionDayForm getForm();
    Map<String, Double> updatePrice(TransitionDayForm form);
    void updateLastId();
    void transitionDay(Map<String, Double> map);
    double buy(Transaction transaction, Map<String, Double> map);
    double sell(Transaction transaction, Map<String, Double> map);
    double deposit(Transaction transaction);
    void withdraw(Transaction transaction);
}
