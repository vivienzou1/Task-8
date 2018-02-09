package com.task7.leo.dto;


import com.task7.leo.domain.State;
import com.task7.leo.validation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ConfirmPasswordCheck
public class CustomerRegisterForm {

    @NotBlank(message = "username can not be empty")
    @StringSanitizer
    @UsernameExistsCheck
    @Size(max = 10)
    private String username;

    @NotBlank(message = "password can not be empty")
    @Size(max = 20)
    private String password;

    @NotBlank(message = "confirm password can not be empty")
    @Size(max = 20)
    private String confirmPassword;

    @Email(message = "email is invalid")
    @StringSanitizer
    @Size(max = 30)
    private String email;

    @NotBlank(message = "first name can not be empty")
    @StringSanitizer
    @Size(max = 10)
    private String firstName;

    @NotBlank(message = "last name can not be empty")
    @StringSanitizer
    @Size(max = 10)
    private String lastName;

    @NotBlank(message = "are you employee or customer?")
    private String type = "customer";

    @Digits(integer = 300, fraction = 2, message = "plz input number, with integer digits smaller than 300 and fraction digits smaller than 3")
    private String balance;

    @StringSanitizer
    @Size(max = 20)
    @NotBlank(message = "plz input the address")
    private String address1;

    @StringSanitizer
    @Size(max = 15)
    private String address2;

    @StringSanitizer
    @NotBlank(message = "plz input the city")
    @Size(max = 10)
    private String city;

    private State state;

    @StringSanitizer
    @NotBlank(message = "plz input the ZipCode")
    @ZipcodeCheck
    private String zipcode;

}
