package com.task7.leo.service.Imp;

import com.task7.leo.domain.Customer;
import com.task7.leo.domain.Employee;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class AuthImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public AuthImpl(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        //this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userRepository.findByUsername(username);
        Customer customer = customerRepository.findCustomerByUsername(username);
        Employee employee = employeeRepository.findEmployeeByUsername(username);

        if (customer == null && employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String role;
        org.springframework.security.core.userdetails.User user;
        if (customer != null) role = customer.getRole();
        else role = employee.getRole();

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));

        if (customer != null) user = new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), authorities);
        else user = new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), authorities);

        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        return user;
    }
}
