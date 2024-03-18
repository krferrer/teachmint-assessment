package com.teachmint.exam.service.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShareUtils {

    public static BigDecimal calculateEqualShare(BigDecimal amount, int numberOfPeople){
        return amount.divide(BigDecimal.valueOf(numberOfPeople), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateRemainingAmount(BigDecimal totalAmount,BigDecimal equalAmount, int numberOfPeople){
        return totalAmount.subtract(equalAmount.multiply(BigDecimal.valueOf(numberOfPeople - 1)));
    }

    public static BigDecimal calculatePercentageAmount(BigDecimal totalAmount,BigDecimal percentage){
        var MAX_PERCENTAGE = BigDecimal.valueOf(100);
        return (totalAmount.divide(MAX_PERCENTAGE)).multiply(percentage);
    }

}
