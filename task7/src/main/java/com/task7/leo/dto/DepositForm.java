package com.task7.leo.dto;


import com.task7.leo.validation.CustomerCheck;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DepositForm {

    @NotBlank(message = "plz input the username")
    @CustomerCheck
    @Size(min = 1, max = 20)
    private String username;

    @Digits(integer = 300, fraction = 2,message = "plz input number, with integer digits smaller than 300 and fraction digits smaller than 3")
    @Range(min = 1, max = 1000000 ,message = "amount number should be in range of 1 to 1000000")
    private String amount;
}
