package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	public String message = "";
	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
		System.out.println("Error "+ message);
	}
	
}
