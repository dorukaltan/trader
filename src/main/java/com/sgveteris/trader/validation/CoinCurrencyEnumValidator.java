package com.sgveteris.trader.validation;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sgveteris.trader.model.CoinCurrencyEnum;

public class CoinCurrencyEnumValidator implements
        ConstraintValidator<CoinCurrencyEnumConstraint, String> {

    final String availableCoinCurrencies = 
                                    Arrays.asList(CoinCurrencyEnum.values())
                                          .stream()
                                          .map(Enum::toString)
                                          .collect(Collectors.joining(","));
    
    @Override
    public boolean isValid(String coinCurrency, ConstraintValidatorContext context) {

        boolean coinCurrencyAvailable = Arrays
                                .stream(CoinCurrencyEnum.values())
                                .anyMatch((t) -> t.name()
                                .equals(coinCurrency));
        if(!coinCurrencyAvailable) {
        	context.buildConstraintViolationWithTemplate(
        			    "Requested coin currency is not available. " +
        			    "Available currencies are: " + availableCoinCurrencies)
        	       .addConstraintViolation();
        	return false;
        }
        return true;
    }

}