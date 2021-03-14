package com.sgveteris.trader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.sgveteris.trader.model.CalculationResponseModel;

import feign.FeignException;

@SpringBootTest
class TraderApplicationTests {

	@Autowired
	private CalculationClient calculationClient;
	
	@Test
	public void correctRequestShouldReturnOKAndCalculation() throws Exception {
		ResponseEntity<CalculationResponseModel> calculation = 
				calculationClient.getCalculation("USD", 50000, "BTC");

		Assert.isTrue(calculation.getStatusCode() == HttpStatus.OK, "HttpStatus is not OK");
		Assert.isTrue(calculation.getBody().getToCoin() != null, "ToCoin is null");
		Assert.isTrue(calculation.getBody().getToCoin().getAmount() > 0, "Amount is not greater than 0");
	}

	@Test
	public void unavailableToCurrencyShouldReturnValidationError() throws Exception {
		FeignException exception = Assertions.assertThrows(FeignException.class, () -> {
			calculationClient.getCalculation("USD", 50000, "IOT");
	    });

		Assert.isTrue(exception.getMessage().contains("Requested coin currency is not available."),
				      "Should have returned correct error message");		
	}
}
