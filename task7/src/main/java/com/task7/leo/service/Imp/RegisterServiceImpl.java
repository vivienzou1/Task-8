package com.task7.leo.service.Imp;

import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Employee;
import com.task7.leo.dto.CustomerRegisterForm;
import com.task7.leo.dto.EmployeeRegisterForm;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.EmployeeRepository;
import com.task7.leo.service.RegisterService;
import com.task7.leo.dto.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class RegisterServiceImpl implements RegisterService {

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public RegisterServiceImpl(BCryptPasswordEncoder encoder, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        //this.userRepository = userRepository;
        this.encoder = encoder;
        //this.roleRepository = roleRepository;
    }

    @Override
    public void Register(UserRegisterForm userRegisterForm) {
        if (userRegisterForm.getType().toLowerCase().equals("customer")) {
            RegisterForCustomer(userRegisterForm);
        } else {
            RegisterForEmployee(userRegisterForm);
        }
    }

    @Override
    public void Register(CustomerRegisterForm customerRegisterForm) {
        RegisterForCustomer(customerRegisterForm);
    }

    @Override
    public void Register(EmployeeRegisterForm employeeRegisterForm) {
        RegisterForEmployee(employeeRegisterForm);
    }

    private void RegisterForCustomer(UserRegisterForm userRegisterForm) {
        Customer customer = new Customer(userRegisterForm);
        customer.setPassword(encoder.encode(userRegisterForm.getPassword()));
        customerRepository.save(customer);
    }

    private void RegisterForEmployee(UserRegisterForm userRegisterForm) {
        Employee employee = new Employee(userRegisterForm);
        employee.setPassword(encoder.encode(userRegisterForm.getPassword()));
        employeeRepository.save(employee);
    }

    private void RegisterForCustomer(CustomerRegisterForm customerRegisterForm) {
        Customer customer = new Customer(customerRegisterForm);
        customer.setPassword(encoder.encode(customerRegisterForm.getPassword()));
        customerRepository.save(customer);
    }

    private void RegisterForEmployee(EmployeeRegisterForm employeeRegisterForm) {
        Employee employee = new Employee(employeeRegisterForm);
        employee.setPassword(encoder.encode(employeeRegisterForm.getPassword()));
        employeeRepository.save(employee);
    }

}
