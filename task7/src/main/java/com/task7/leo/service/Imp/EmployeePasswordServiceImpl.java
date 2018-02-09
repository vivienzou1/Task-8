package com.task7.leo.service.Imp;

import com.task7.leo.dto.EmployeePasswordForm;
import com.task7.leo.repositories.EmployeeRepository;
import com.task7.leo.service.EmployeePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class EmployeePasswordServiceImpl implements EmployeePasswordService {

    private final EmployeeRepository employeeRepository;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public EmployeePasswordServiceImpl(BCryptPasswordEncoder encoder, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
    }

    public void resetPassword(EmployeePasswordForm employeePasswordForm, long id) {
        String password = encoder.encode(employeePasswordForm.getPassword());
        employeeRepository.updatePasswordById(password, id);
    }
}
