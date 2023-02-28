package com.nomaanlearn.rest.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//these should be in subpackage of resfulwebserviceapplication
@RestController
public class HelloWorldController {
	private MessageSource messageSource;
	
	
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping(path = "/hello-world")
	public String helloWold() {
		return "Hello World";
	}
	
	//to get response back in different languages we accept a header Accept-Language for that 
	//we need to create different messages.properties files for each language, check resources folder
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWoldInternationalized() {
		//This will get the locale from the header
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "default message", locale);
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWoldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/hello-world-bean/variable/{message}")
	public HelloWorldBean helloWoldVaraible(@PathVariable String message) {
		
		
		
		return new HelloWorldBean("Hello World, "+message);
	}

}
