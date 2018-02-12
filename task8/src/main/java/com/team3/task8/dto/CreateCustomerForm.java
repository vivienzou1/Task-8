package com.team3.task8.dto;

import com.team3.task8.validation.CashCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateCustomerForm {

    @NotNull
    private String fname;

    @NotNull
    private String lname;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zip;

    @NotNull
    private String email;

    @CashCheck
    private String cash;

    @NotNull
    private String username;

    @NotNull
    private String password;

}