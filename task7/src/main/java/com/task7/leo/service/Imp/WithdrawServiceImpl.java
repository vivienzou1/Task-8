package com.task7.leo.service.Imp;


import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Transaction;
import com.task7.leo.dto.WithdrawForm;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.TransactionRepository;
import com.task7.leo.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class WithdrawServiceImpl implements WithdrawService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public WithdrawServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void withdraw(WithdrawForm withdrawForm, String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        double withDrawMoney = Double.parseDouble(withdrawForm.getAmount());

        if (withDrawMoney > customer.getBalance()) {
            throw new RuntimeException("balance.not.enough");
        }

        Transaction transaction = new Transaction(customer, null, withDrawMoney, 0, 0, "withdraw");
        transactionRepository.save(transaction);

        double newBalance = customer.getBalance() - withDrawMoney;
        customerRepository.updateBalanceById(newBalance, customer.getId());
    }
}
