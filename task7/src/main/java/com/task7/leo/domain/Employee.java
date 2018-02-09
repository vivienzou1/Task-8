package com.task7.leo.domain;

import com.task7.leo.dto.EmployeeRegisterForm;
import com.task7.leo.dto.UserRegisterForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    public Employee(UserRegisterForm userRegisterForm) {
        this.role = "ROLE_EMPLOYEE";
        this.username = userRegisterForm.getUsername();
        this.email = userRegisterForm.getEmail();
        this.firstName = userRegisterForm.getFirstName();
        this.lastName = userRegisterForm.getLastName();
    }

    public Employee(EmployeeRegisterForm employeeRegisterForm) {
        this.role = "ROLE_EMPLOYEE";
        this.username = employeeRegisterForm.getUsername();
        this.email = employeeRegisterForm.getEmail();
        this.firstName = employeeRegisterForm.getFirstName();
        this.lastName = employeeRegisterForm.getLastName();
    }
}
