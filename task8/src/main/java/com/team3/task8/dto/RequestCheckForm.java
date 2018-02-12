package com.team3.task8.dto;

import com.team3.task8.validation.CashCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RequestCheckForm {

    @NotNull
    @CashCheck
    private String cashValue;

}