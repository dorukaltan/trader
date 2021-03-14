package com.sgveteris.trader.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgveteris.trader.model.CalculationRequestModel;
import com.sgveteris.trader.model.CalculationResponseModel;
import com.sgveteris.trader.model.CoinCurrencyEnum;
import com.sgveteris.trader.model.PriceCurrencyEnum;
import com.sgveteris.trader.model.PriceModel;
import com.sgveteris.trader.service.CalculationService;
import com.sgveteris.trader.validation.CoinCurrencyEnumConstraint;

@RestController
@RequestMapping("/calculate")
@Validated
public class CalculationController {

	@Autowired
	private CalculationService calculationService;

	@GetMapping("/{fromCurrency}/{fromAmount}/{toCurrency}")
	public CalculationResponseModel calculate(
			@PathVariable("fromCurrency") String fromCurrencyString,
			@PathVariable("fromAmount") @Min(25) @Max(50000) double fromAmount,
			@PathVariable("toCurrency") @Valid @CoinCurrencyEnumConstraint String toCurrencyString)
	{
		CalculationRequestModel request = new CalculationRequestModel();
		request.setFromPrice(PriceModel.builder()
							.currency(PriceCurrencyEnum.valueOf(fromCurrencyString))
							.amount(fromAmount).build());
		request.setToCurency(CoinCurrencyEnum.valueOf(toCurrencyString));
		return calculationService.calculate(request);
	}
}
