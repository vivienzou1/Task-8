package com.team3.task8.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}