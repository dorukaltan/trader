package com.sgveteris.trader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sgveteris.trader.apiclient.blockchain.BlockChainClient;
import com.sgveteris.trader.apiclient.blockchain.BlockChainTickerResponseModel;
import com.sgveteris.trader.apiclient.blockchain.TickerModel;
import com.sgveteris.trader.exception.BlockChainClientException;
import com.sgveteris.trader.model.CalculationRequestModel;
import com.sgveteris.trader.model.CalculationResponseModel;
import com.sgveteris.trader.model.CoinCurrencyEnum;
import com.sgveteris.trader.model.CoinModel;
import com.sgveteris.trader.model.PriceCurrencyEnum;

@Service
public class CalculationService {

	@Autowired
	private BlockChainClient blockChainClient;

	public CalculationResponseModel calculate(CalculationRequestModel request) {

		ResponseEntity<BlockChainTickerResponseModel> exchangeRatesResponse =
					blockChainClient.getExchangeRates();

		if(exchangeRatesResponse.getStatusCode() != HttpStatus.OK) {
			throw new BlockChainClientException("Unable to fetch exchangeRates");
		}

		BlockChainTickerResponseModel exchangeRates = exchangeRatesResponse.getBody();
		TickerModel rates = exchangeRates.getUsdRates();

		PriceCurrencyEnum fromPriceCurrency = request.getFromPrice().getCurrency();
		double fromPriceAmount = request.getFromPrice().getAmount();

		//request.getToCurency(); has to be btc
		//blockChain api only returns btc values
		//we also know that any other coin calculation will not make it here

		double toCoinAmount = fromPriceAmount / rates.getBuy();
		
		CoinModel toCoin = CoinModel.builder()
							.amount(toCoinAmount)
							.currency(CoinCurrencyEnum.BTC)
							.build();


		CalculationResponseModel response = new CalculationResponseModel();
		response.setFromPrice(request.getFromPrice());
		response.setToCoin(toCoin);
		return response;
	}
}
