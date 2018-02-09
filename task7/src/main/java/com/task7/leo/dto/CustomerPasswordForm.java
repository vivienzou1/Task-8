package com.task7.leo.dto;


import com.task7.leo.validation.ConfirmPasswordCheck;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ConfirmPasswordCheck
public class CustomerPasswordForm {

    @NotBlank(message = "password can not be empty")
    @Size(min = 1, max = 20)
    private String password;

    @NotBlank(message = "confirm password can not be empty")
    @Size(min = 1, max = 20)
    private String confirmPassword;


}
