package com.nomaanlearn.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes() //to match based on header do p.header, you can match based on many parameters
				.route(p->p.path("/get")//if localhost:8765/get is hit reroute it to httbin.org:80 after adding headers and params
						.filters(f->
						f.addRequestHeader("MyHeader", "MyHeaderValue")
						.addRequestParameter("MyParams", "MyParamsValue"))
						.uri("http://httpbin.org:80"))
				.route(p->p.path("/currency-exchange/**")//if path starts with currency-exchange/ redirect
						.uri("lb://currency-exchange"))//as name on eureka naming server, lb is added to tell eureka to load balance this
				.route(p->p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(p->p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(p->p.path("/currency-conversion-new/**") //we want to redirect this too to currency conversion
						.filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)", //regex to take all text after -new/
								"/currency-conversion-feign/${segment}")) //and put it here
						.uri("lb://currency-conversion")) 
				.build();
	}
}
