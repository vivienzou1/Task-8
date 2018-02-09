package com.task7.leo.service.Imp;

import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Transaction;
import com.task7.leo.dto.DepositForm;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.TransactionRepository;
import com.task7.leo.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class DepositServiceImpl implements DepositService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public DepositServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void deposit(DepositForm depositForm) {
        Customer customer = customerRepository.findCustomerByUsername(depositForm.getUsername());
        double depositMoney = Double.parseDouble(depositForm.getAmount());

        if (customer.getBalance() > Double.MAX_VALUE - depositMoney) {
            throw new RuntimeException("too.much.money");
        }

        Transaction transaction = new Transaction(customer, null, depositMoney, 0,0, "deposit");
        transactionRepository.save(transaction);
    }
}
