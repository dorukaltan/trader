package com.sgveteris.trader.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sgveteris.trader.model.CoinCurrencyEnum;

@Documented
@Constraint(validatedBy = CoinCurrencyEnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CoinCurrencyEnumConstraint {
	
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}