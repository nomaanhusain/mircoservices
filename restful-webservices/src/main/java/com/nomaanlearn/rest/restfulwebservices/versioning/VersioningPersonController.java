package com.nomaanlearn.rest.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	
	//This is url versioning
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	
	//Versioning with request parameters
	//http://localhost:8080/person?version=1
	@GetMapping(path="person",  params = "version=1")
	public PersonV1 getPersonV1ReqPrams() {
		return new PersonV1("Bob Charlie");
	}
	//http://localhost:8080/person?version=2
	@GetMapping(path="person",  params = "version=2")
	public PersonV2 getPersonV2ReqParams() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	
	//Versioning with headers
	@GetMapping(path="person",  headers = "version=1")
	public PersonV1 getPersonV1Header() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path="person",  headers = "version=2")
	public PersonV2 getPersonV2Header() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//Versioning with Media Types ie MIME types aka content negotiation or Accept header
	//In header put Accept and value as application/vdn.company.app-V1+json
	@GetMapping(path="person",  produces = "application/vdn.company.app-V1+json")
	public PersonV1 getPersonV1Mime() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path="person",  produces = "application/vdn.company.app-V2+json")
	public PersonV2 getPersonV2Mime() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}
