package com.sgveteris.trader.model;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationResponseModel {
	private ZonedDateTime zonedDateTime;
	private PriceModel fromPrice;
	private CoinModel toCoin;
}
