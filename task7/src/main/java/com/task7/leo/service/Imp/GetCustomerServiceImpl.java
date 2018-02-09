package com.task7.leo.service.Imp;

import com.task7.leo.domain.Customer;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.service.GetCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class GetCustomerServiceImpl implements GetCustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public GetCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer currentCustomer(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        return customer;
    }

    public Customer customerById(long id) {
        Customer customer = customerRepository.findById(id);
        return customer;
    }
}
