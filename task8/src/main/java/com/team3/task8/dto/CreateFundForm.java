package com.team3.task8.dto;

import com.team3.task8.validation.CashCheck;
import com.team3.task8.validation.ShareCheck;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateFundForm {

    @NotBlank
    private String name;

    @NotBlank
    private String symbol;

    @CashCheck
    @NotBlank
    private String initial_value;

}