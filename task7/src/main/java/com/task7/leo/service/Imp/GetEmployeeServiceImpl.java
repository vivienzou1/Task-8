package com.task7.leo.service.Imp;

import com.task7.leo.domain.Employee;
import com.task7.leo.repositories.EmployeeRepository;
import com.task7.leo.service.GetEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation  = Isolation.SERIALIZABLE)
public class GetEmployeeServiceImpl implements GetEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public GetEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee currentEmployee(String username) {
        Employee employee = employeeRepository.findEmployeeByUsername(username);
        return employee;
    }
}
