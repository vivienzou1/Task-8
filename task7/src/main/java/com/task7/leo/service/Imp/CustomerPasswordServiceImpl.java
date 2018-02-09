package com.task7.leo.service.Imp;

import com.task7.leo.dto.CustomerPasswordForm;
import com.task7.leo.repositories.CustomerRepository;
import com.task7.leo.service.CustomerPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class CustomerPasswordServiceImpl implements CustomerPasswordService {

    private final CustomerRepository customerRepository;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public CustomerPasswordServiceImpl(BCryptPasswordEncoder encoder, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }

    public void resetPassword(CustomerPasswordForm customerPasswordForm, long id) {
        String password = encoder.encode(customerPasswordForm.getPassword());
        customerRepository.updatePasswordById(password, id);
    }

    public void resetPasswordByUsername(CustomerPasswordForm customerPasswordForm, String username) {
        String password = encoder.encode(customerPasswordForm.getPassword());
        customerRepository.updatePasswordByUsername(password, username);
    }
}
