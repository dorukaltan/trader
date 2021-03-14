package com.sgveteris.trader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationRequestModel {
	private PriceModel fromPrice;
	private CoinCurrencyEnum toCurency;
}
