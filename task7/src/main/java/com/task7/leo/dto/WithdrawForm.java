package com.task7.leo.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class WithdrawForm {

    @Digits(integer = 300, fraction = 2,message = "plz input number, with integer digits smaller than 300 and fraction digits smaller than 3")
    @Range(min = 0, max = 1000000, message = "amount number should be in range of 1 to 1000000")
    private String amount;
}
