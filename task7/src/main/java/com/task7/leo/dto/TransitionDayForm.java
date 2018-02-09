package com.task7.leo.dto;


import com.task7.leo.validation.DateFormatCheck;
import com.task7.leo.validation.TransitionDateCheck;
import com.task7.leo.validation.TransitionDayPriceCheck;
import com.task7.leo.validation.TransitionFundCheck;
import com.task7.leo.validation.TransitionSymbolCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TransitionDayForm {


    private List<String> hiddenSymbols;
    private List<String> hiddenPrices;

    @TransitionSymbolCheck
    private List<String> fundSymbols;

    @TransitionFundCheck
    @TransitionDayPriceCheck
    private List<String> fundPrices;

    @TransitionDateCheck
    @DateFormatCheck
    private Date date;
}
