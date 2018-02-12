package com.team3.task8.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;
}