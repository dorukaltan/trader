package com.sgveteris.trader;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sgveteris.trader.model.CalculationResponseModel;

@FeignClient(name = "calculation", url = "http://localhost:8080")
public interface CalculationClient {

	@RequestMapping(method = RequestMethod.GET,
			value = "/calculate/{fromCurrency}/{fromAmount}/{toCurrency}") 
	ResponseEntity<CalculationResponseModel> getCalculation(
			@PathVariable("fromCurrency") String fromCurrencyString,
			@PathVariable("fromAmount") double fromAmount,
			@PathVariable("toCurrency") String toCurrencyString);

}
