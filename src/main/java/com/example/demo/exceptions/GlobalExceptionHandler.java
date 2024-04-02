package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.SimpleReponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<SimpleReponseDTO> handleResourcerNotFoundException(ResourceNotFoundException ex, WebRequest wr){
		return(new ResponseEntity<SimpleReponseDTO>(new SimpleReponseDTO(ex.getMessage(), "Data Error"),HttpStatus.NOT_FOUND));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<SimpleReponseDTO> handleException(Exception ex, WebRequest wr){
		return(new ResponseEntity<SimpleReponseDTO>(new SimpleReponseDTO(ex.getMessage(), "System Error"),HttpStatus.INTERNAL_SERVER_ERROR));
	}
}
