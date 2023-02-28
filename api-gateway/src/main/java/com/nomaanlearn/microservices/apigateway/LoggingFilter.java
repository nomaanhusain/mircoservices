package com.nomaanlearn.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{
	
	//Here we are using this to log to console the request path
	//but here we can implement global filters (i.e add headers and paramenters) like we did in ApiGatewayConfiguration.java file
	//authentication generally is implemented here
	private Logger logger=LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("Path of the request received: {}",exchange.getRequest().getPath());
		return chain.filter(exchange);
	}
	

}
