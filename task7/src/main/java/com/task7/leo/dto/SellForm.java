package com.task7.leo.dto;


import com.task7.leo.validation.FundSymbolCheck;
import com.task7.leo.validation.StringSanitizer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SellForm {

    @Size(min = 1, max = 5, message = "length of the fundSymbol should be in 1 to 5")
    @StringSanitizer
    @FundSymbolCheck
    private String fundSymbol;

    @Digits(integer = 300, fraction = 3,message = "plz input number, with integer digits smaller than 300 and fraction digits smaller than 4")
    @Range(min = 0, max = 1000000, message = "share number should be in range of 0 to 1000000")
    private String share;
}
