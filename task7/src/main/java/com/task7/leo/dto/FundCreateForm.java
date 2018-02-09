package com.task7.leo.dto;


import com.task7.leo.validation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class FundCreateForm {

    @NotBlank(message = "fund name can not be empty")
    @Size(min = 1, max = 10, message = "length of the fundName should be in 1 to 10")
    @StringSanitizer
    @FundNameExistsCheck
    @NameFormatCheck
    private String fundName;

    @NotBlank(message = "fund symbol can not be empty")
    @Size(min = 1, max = 5, message = "length of the fundSymbol should be in 1 to 5")
    @SymbolFormatCheck
    @StringSanitizer
    @FundSymbolExistsCheck
    private String fundSymbol;

    @Digits(integer = 4, fraction = 2, message = "plz input number, with integer digits smaller than 300 and fraction digits smaller than 3")
    @Range(min = 1, max = 1000, message = "your price should be from 1 - 1000")
    private String price;
}
