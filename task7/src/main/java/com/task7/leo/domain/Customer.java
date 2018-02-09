package com.task7.leo.domain;

import com.task7.leo.dto.CustomerRegisterForm;
import com.task7.leo.dto.UserRegisterForm;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    private double balance;

    private String address1;

    private String address2;

    private String city;

    private State state;

    private String zipcode;

    @OneToMany
    private List<FundHold> fundHolds;

    @OneToMany
    private List<Transaction> transactions;

    public Customer(UserRegisterForm userRegisterForm) {
        this.role = "ROLE_CUSTOMER";
        this.username = userRegisterForm.getUsername();
        this.email = userRegisterForm.getEmail();
        this.firstName = userRegisterForm.getFirstName();
        this.lastName = userRegisterForm.getLastName();
        if (userRegisterForm.getBalance() != null) {
            this.balance = Double.parseDouble(userRegisterForm.getBalance());
        } else {
            this.balance = 0.0;
        }
        this.fundHolds = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public Customer(CustomerRegisterForm customerRegisterForm) {
        this.role = "ROLE_CUSTOMER";
        this.username = customerRegisterForm.getUsername();
        this.email = customerRegisterForm.getEmail();
        this.firstName = customerRegisterForm.getFirstName();
        this.lastName = customerRegisterForm.getLastName();
        if (customerRegisterForm.getBalance() != null) {
            this.balance = Double.parseDouble(customerRegisterForm.getBalance());
        } else {
            this.balance = 0.0;
        }
        this.fundHolds = new ArrayList<>();
        this.transactions = new ArrayList<>();

        this.address1 = "";
        if (customerRegisterForm.getAddress1() != null) this.address1 = customerRegisterForm.getAddress1();
        this.address2 = "";
        if (customerRegisterForm.getAddress2() != null) this.address2 = customerRegisterForm.getAddress2();
        this.city = "";
        if (customerRegisterForm.getCity() != null) this.city = customerRegisterForm.getCity();
        this.state = null;
        if (customerRegisterForm.getState() != null) this.state = customerRegisterForm.getState();
        this.zipcode = "";
        if (customerRegisterForm.getZipcode() != null) this.zipcode = customerRegisterForm.getZipcode();

    }
}
