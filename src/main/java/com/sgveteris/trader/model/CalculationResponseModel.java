package com.sgveteris.trader.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculationResponseModel {
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private ZonedDateTime expireZonedDateTime;
	private PriceModel fromPrice;
	private CoinModel toCoin;
}
