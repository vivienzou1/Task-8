package com.task7.leo.service.Imp;

import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Fund;
import com.task7.leo.domain.Transaction;
import com.task7.leo.dto.BuyForm;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.FundRepository;
import com.task7.leo.repositories.TransactionRepository;
import com.task7.leo.service.BuyFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class BuyFundServiceImpl implements BuyFundService {

    private final CustomerRepository customerRepository;
    private final FundRepository fundRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BuyFundServiceImpl(CustomerRepository customerRepository, FundRepository fundRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.fundRepository = fundRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void buyFund(BuyForm buyForm, String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        // check balance
        double cost = Double.parseDouble(buyForm.getAmount());
        if (customer.getBalance() < cost) {
            throw new RuntimeException("balance.not.enough");
        }

        Fund fund = fundRepository.findByFundSymbol(buyForm.getFundSymbol());

        Transaction transaction = new Transaction(customer, fund, cost, 0,0, "buy");
        transactionRepository.save(transaction);
        double newBalance = customer.getBalance() - cost;
        customerRepository.updateBalanceById(newBalance, customer.getId());
        customer.getTransactions().add(transaction);
    }
}
