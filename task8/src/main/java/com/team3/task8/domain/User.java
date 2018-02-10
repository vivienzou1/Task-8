package com.team3.task8.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String password;

    private String fname;

    private String lname;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String email;

    private double cash;

    private String role;

    public User(String fname,
                String lname,
                String address,
                String city,
                String state,
                String zip,
                String email,
                double cash,
                String role,
                String username,
                String password) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.cash = cash;
        this.role = role;
        this.username = username;
        this.password = password;
    }
}