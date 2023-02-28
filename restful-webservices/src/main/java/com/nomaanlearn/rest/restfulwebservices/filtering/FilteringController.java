package com.nomaanlearn.rest.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("data1","data2","data3");
		
		//In Dynamic filtering, you want to filer out properties of a response for a specific API call and not for all the 
		//api calls returning the same object, like here we want different response for /filtering and /filtering-list
		//this is for dynamic filtering
		
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue getSomeBeanList() {
		
		
		List<SomeBean> beanList = Arrays.asList(new SomeBean("data11","data12","data13"),
				new SomeBean("data21","data22","data23"));
		
		//here we will filter out field1
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(beanList);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
}
