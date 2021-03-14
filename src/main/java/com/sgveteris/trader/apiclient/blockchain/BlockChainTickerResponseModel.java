package com.sgveteris.trader.apiclient.blockchain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockChainTickerResponseModel {
	
	@JsonProperty("USD")
	private TickerModel usdRates; 
}
