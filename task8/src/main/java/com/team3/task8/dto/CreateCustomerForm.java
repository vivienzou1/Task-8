package com.team3.task8.dto;

import com.team3.task8.validation.CashCheck;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateCustomerForm {

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String zip;

    @NotBlank
    private String email;

    @CashCheck
    private String cash;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}