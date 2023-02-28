package com.nomaanlearn.rest.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;
	
	public UserResource(UserDaoService service) {
		this.service=service;
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return service.getAllUser();
	}
	
	
	//We will implement links using hateoas here, it provides a standardized way of adding links to a api response
	//EntityModel and WebMvcLinkBuilder are part of it
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable int id) {
		User userById = service.getUserById(id);
		if(userById==null) {
			throw new UserNotFoundException("User with id: "+id +" not found");
		}
		
		EntityModel<User> entityModel=EntityModel.of(userById);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		service.deleteUserById(id);
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser=service.createUser(user);
		
		/**
		ServletUriComponentsBuilder.fromCurrentRequest() will give url of current request
		we want to expand that with the id of the new user and return that as part of the response
		**/
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();	
		//TO return response status as 201 with uri
		return ResponseEntity.created(location).build();
	}
}
