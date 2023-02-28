package com.nomaanlearn.rest.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * We want to redefine the filter chain for spring security,
 * currently there is a web page displayed for authentication, we want a popup
 * also we want to enable post and put request for which we need to disable CSRF
 */

@Configuration
public class SpringSecurityConfigration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//All request should be authenticated
		http.authorizeHttpRequests(auth->
			auth.anyRequest().authenticated());
		
		//If request is not authenticated, popup should be shown and not a webpage
		http.httpBasic(Customizer.withDefaults());
		
		//disable CSRF to enable post, put requests
		http.csrf().disable();
		
		return http.build();
	}

}
