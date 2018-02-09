package com.task7.leo.dto;


import com.task7.leo.validation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Digits;

@Data
@NoArgsConstructor
@ConfirmPasswordCheck
public class UserRegisterForm {

    @NotBlank(message = "username can not be empty")
    @StringSanitizer
    @UsernameExistsCheck
    private String username;

    @NotBlank(message = "password can not be empty")
    @StringSanitizer
    private String password;

    @NotBlank(message = "confirm password can not be empty")
    @StringSanitizer
    private String confirmPassword;

    @Email(message = "email is in valid")
    @StringSanitizer
    private String email;

    @NotBlank(message = "first name can not be empty")
    @StringSanitizer
    private String firstName;

    @NotBlank(message = "last name can not be empty")
    @StringSanitizer
    private String lastName;

    @NotBlank(message = "are you employee or customer?")
    private String type;

    @Digits(integer = 300, fraction = 2, message = "plz input number, with integer digits smaller than 300 and fraction digits smaller than 3")
    private String balance;
}
