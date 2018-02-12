package com.team3.task8.dto;

import com.team3.task8.validation.CashCheck;
import com.team3.task8.validation.ShareCheck;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SellFundForm {

    @NotNull
    private String symbol;

    @NotNull
    @ShareCheck
    @Range(min = 1)
    private String numShares;

}