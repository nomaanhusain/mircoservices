package com.nomaanlearn.rest.restfulwebservices.exception;


import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nomaanlearn.rest.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request){
		ex.printStackTrace();
		ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request){
		ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
	//for validation errors from @Valid arguments
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		
		
		String errors = "";
		for(ObjectError i : ex.getAllErrors()) {
			errors=errors+i.getDefaultMessage()+" || ";
		}
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				errors, request.getDescription(false));
		
		
		
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
