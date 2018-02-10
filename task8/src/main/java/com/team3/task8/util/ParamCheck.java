package com.team3.task8.util;

import org.springframework.stereotype.Service;

@Service
public class ParamCheck {

    public static boolean isInteger(String shares) {

        try {
            Integer.parseInt(shares);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean isTwoDecimal(String cash) {

        try {
            Double.parseDouble(cash);
        } catch (NumberFormatException e) {
            return false;
        }

        if (cash.split("\\.").length == 1 || cash.split("\\.")[1].length() <= 2)
        {
            return true;
        }

        return false;
    }

    public static boolean isMultiple(double d1, double d2) {

        double threshold = 1E-6;

        if (Math.abs(d2 - (int)(d1 / d2) * d2) > threshold) {
            return false;
        }

        return true;
    }
}
