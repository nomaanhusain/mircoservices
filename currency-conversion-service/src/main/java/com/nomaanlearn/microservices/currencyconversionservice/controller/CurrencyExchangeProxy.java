package com.nomaanlearn.microservices.currencyconversionservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nomaanlearn.microservices.currencyconversionservice.model.CurrencyConversion;
/**
By adding the eureka client dependency and removing the url field, this api will automatically get loadbalanced
from Eureka, we dont need to add anything special, the url will be handled by eureka
**/
//@FeignClient(name="currency-exchange", url = "localhost:8000")
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retriveExchangeRates(@PathVariable String from,@PathVariable String to);

}
