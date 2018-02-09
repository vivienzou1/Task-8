package com.task7.leo.dto;


import com.task7.leo.validation.StringSanitizer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SearchFundForm {


    @Size(min = 3, max = 5, message = "length of the fundSymbol should be in 3 to 5")
    @StringSanitizer
    private String fundSymbol;
}
