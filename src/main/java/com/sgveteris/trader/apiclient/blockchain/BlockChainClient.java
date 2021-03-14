package com.sgveteris.trader.apiclient.blockchain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "blockChain", url = "https://blockchain.info")
public interface BlockChainClient {

	@Cacheable("blockChainRates")
	@RequestMapping(method = RequestMethod.GET, value = "/ticker") 
	ResponseEntity<BlockChainTickerResponseModel> getExchangeRates();

//	@RequestMapping(method = RequestMethod.GET, value = "/tobtc") 
//	ResponseEntity<BlockChainToBtcResponseModel> convertToBtc(
//				@RequestParam("currency") String currency,
//				@RequestParam("value") String value);
	
}
