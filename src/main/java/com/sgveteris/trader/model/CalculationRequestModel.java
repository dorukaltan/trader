package com.sgveteris.trader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculationRequestModel {
	private PriceModel fromPrice;
	private CoinCurrencyEnum toCurency;
}
