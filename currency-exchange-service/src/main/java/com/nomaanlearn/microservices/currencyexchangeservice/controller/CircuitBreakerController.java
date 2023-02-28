package com.nomaanlearn.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


//building this contrller as an example for resileince4j ie. circuit breaker
@RestController
public class CircuitBreakerController {

	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
	/*
	 *with name="default" this will retry 3 times on failure otherwise add in application.properties
	 */
//	@Retry(name="myResilienceLogic", fallbackMethod = "onError")
	
	/*
	 * If a request is failing again and again after being called 100-200 times, this will directly call the fallback
	 * method without even executing logic inside the function under the assumption that the logic might still be failing
	 */
	@CircuitBreaker(name="myResilienceLogic", fallbackMethod = "onError")
	/*
	 * Defines the number of calls allowed in a certain period of time default is
	 * 10000 calls every 10 secs
	 */
//	@RateLimiter(name="myResilienceLogic")
	/*
	 * Defines how many concurrenct calls are allowed
	 */
//	@Bulkhead(name="myResilienceLogic")
	public String sampleApi() {
		logger.info("sample call recieved");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-call", String.class);
		return forEntity.getBody();
		//return "success";
	}
	public String onError(Exception ex) {
		return "fallback-response: error occured";
	}
}
