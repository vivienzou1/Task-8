package com.task7.leo.service;

import com.task7.leo.domain.Customer;

public interface GetCustomerService {

    Customer currentCustomer(String username);
    Customer customerById(long id);
}
