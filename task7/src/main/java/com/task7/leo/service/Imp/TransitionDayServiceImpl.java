package com.task7.leo.service.Imp;

import com.task7.leo.domain.*;
import com.task7.leo.dto.TransitionDayForm;
import com.task7.leo.repositories.*;
import com.task7.leo.service.TransitionDayService;
import com.task7.leo.validation.TransitionDateCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class TransitionDayServiceImpl implements TransitionDayService {

    private final FundRepository fundRepository;
    private final TransactionRepository transactionRepository;
    private final LastTransitionIdRepository lastTransitionIdRepository;
    private final FundHoldRepository fundHoldRepository;
    private final CustomerRepository customerRepository;
    private Date date;
    private long lastId;

    @Autowired
    public TransitionDayServiceImpl(FundRepository fundRepository, TransactionRepository transactionRepository, LastTransitionIdRepository lastTransitionIdRepository, FundHoldRepository fundHoldRepository, CustomerRepository customerRepository) {
        this.fundRepository = fundRepository;
        this.transactionRepository = transactionRepository;
        this.lastTransitionIdRepository = lastTransitionIdRepository;
        this.fundHoldRepository = fundHoldRepository;
        this.customerRepository = customerRepository;
        if (lastTransitionIdRepository.findAll().size() == 0) {
            LastTransitionId lastTransitionId = new LastTransitionId((long)0);
            lastTransitionIdRepository.save(lastTransitionId);
        }
    }

    @Override
    public void transitionDay(Map<String, Double> map) {
        // for debug ******************
        //lastTransitionIdRepository.findById(1);
        lastId = lastTransitionIdRepository.findById((long)1).getLastTransitionId();
        List<Transaction> transactions = transactionRepository.findByIdAfter(lastId);
        Map<Customer, Double> moneyMap = new ConcurrentHashMap<>();
        Map<Customer, Map<Fund, Double>> shareMap = new ConcurrentHashMap<>();

        for (Transaction transaction : transactions) {
            Customer customer = transaction.getCustomer();
            switch (transaction.getType()) {
                case "buy":
                    shareMap.putIfAbsent(customer, new ConcurrentHashMap<>());
                    double share = buy(transaction, map);
                    Map<Fund, Double> temmap = shareMap.get(customer);
                    temmap.putIfAbsent(transaction.getFund(), 0.0);
                    temmap.put(transaction.getFund(), temmap.get(transaction.getFund()) + share);
                    break;
                case "sell": {
                    moneyMap.putIfAbsent(customer, 0.0);
                    double money = sell(transaction, map);
                    moneyMap.put(customer, moneyMap.get(customer) + money);
                    break;
                }
                case "deposit": {
                    moneyMap.putIfAbsent(customer, 0.0);
                    double money = deposit(transaction);
                    moneyMap.put(customer, moneyMap.get(customer) + money);
                    break;
                }
                case "withdraw": {
                    withdraw(transaction);
                    break;
                }
            }
            lastId = Math.max(lastId, transaction.getId());
        }
        for (Map.Entry<Customer, Double> entry : moneyMap.entrySet()) {
            double newBalance = entry.getKey().getBalance() + entry.getValue();
            customerRepository.updateBalanceById(newBalance, entry.getKey().getId());
        }

        for (Map.Entry<Customer, Map<Fund, Double>> entry : shareMap.entrySet()) {
            Customer customer = entry.getKey();
            for (Map.Entry<Fund, Double> e : entry.getValue().entrySet()) {
                Fund fund = e.getKey();
                double share = e.getValue();
                FundHold fundHold = fundHoldRepository.findByCustomerAndFund(customer,fund);
                if (fundHold == null) {
                    fundHold = new FundHold();
                    fundHold.setFund(fund);
                    fundHold.setCustomer(customer);
                    fundHold.setShare(share);
                    fundHoldRepository.save(fundHold);
                    customer.getFundHolds().add(fundHold);
                } else {
                    double newShare = share + fundHold.getShare();
                    fundHoldRepository.updateShareById(newShare, fundHold.getId());
                }
            }
        }
    }

    public double buy(Transaction transaction, Map<String, Double> map) {
        transactionRepository.updateDateById(date, transaction.getId());
        Fund fund = transaction.getFund();
        double amount = transaction.getAmount();
        double price = map.get(fund.getFundSymbol());
        double share = amount / price;

        transactionRepository.updateShareById(share, transaction.getId());
        transactionRepository.updatePriceById(price, transaction.getId());
        return share;
    }

    public double sell(Transaction transaction,Map<String, Double> map) {
        transactionRepository.updateDateById(date, transaction.getId());
        Fund fund = transaction.getFund();
        double share = transaction.getShare();
        double price = map.get(fund.getFundSymbol());
        double money = share * price;

        transactionRepository.updateAmountById(money, transaction.getId());
        transactionRepository.updatePriceById(price, transaction.getId());

        return money;
    }

    public double deposit(Transaction transaction) {
        transactionRepository.updateDateById(date, transaction.getId());
        return transaction.getAmount();
    }

    public void withdraw(Transaction transaction) {
        transactionRepository.updateDateById(date, transaction.getId());
    }

    public TransitionDayForm getForm() {
        List<Fund> funds = fundRepository.findAll();
        TransitionDayForm transitionDayForm = new TransitionDayForm();

        List<String> fundPrices = new ArrayList<>();
        List<String> fundSymbols = new ArrayList<>();
        List<String> hiddenSymbols = new ArrayList<>();
        List<String> hiddenPrices = new ArrayList<>();
        for (Fund fund : funds) {
            fundPrices.add("");
            hiddenPrices.add(String.valueOf(fund.getPrice()));
            fundSymbols.add("");
            hiddenSymbols.add(fund.getFundSymbol());
        }
        transitionDayForm.setFundPrices(fundPrices);
        transitionDayForm.setFundSymbols(fundSymbols);
        transitionDayForm.setHiddenSymbols(hiddenSymbols);
        transitionDayForm.setHiddenPrices(hiddenPrices);
        return transitionDayForm;
    }

    public Map<String, Double> updatePrice(TransitionDayForm transitionDayForm) {
        Map<String, Double> newPrices = new ConcurrentHashMap<>();
        this.date = transitionDayForm.getDate();
        for (int i = 0; i < transitionDayForm.getFundSymbols().size(); i++) {
            double newPrice = Double.parseDouble(transitionDayForm.getFundPrices().get(i));
            String symbol = transitionDayForm.getFundSymbols().get(i);
            List<String> priceLine = fundRepository.findByFundSymbol(symbol).getPriceLine();
            for (String j : priceLine) {
                System.out.println(j);
            }
            String priceline = date.toString() + "#" + newPrice;
            priceLine.add(priceline);
            newPrices.put(symbol, newPrice);

            //fundRepository.updatePriceLineBySymbol(priceLine, symbol);
            fundRepository.updatePriceBySymbol(newPrice, symbol);
            fundRepository.updateLastTransitionById(date, symbol);
        }
        return newPrices;
    }
    public void updateLastId() {
        lastTransitionIdRepository.updateLastIdById(lastId, (long)1);
    }
}
