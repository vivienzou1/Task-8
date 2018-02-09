package com.task7.leo.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class StringToDateConverter implements Converter<String,Date> {
    public Date convert(String source) {
        Date ddate;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            ddate=sdf.parse(source);
        } catch (Exception e) {
            throw new RuntimeException("date format wrong");
        }
        return ddate;
    }
}
