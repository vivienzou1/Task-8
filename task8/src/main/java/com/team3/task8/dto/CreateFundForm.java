package com.team3.task8.dto;

import com.team3.task8.validation.CashCheck;
import com.team3.task8.validation.ShareCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateFundForm {

    @NotNull
    private String name;

    @NotNull
    private String symbol;

    @CashCheck
    @NotNull
    private String initialValue;

}