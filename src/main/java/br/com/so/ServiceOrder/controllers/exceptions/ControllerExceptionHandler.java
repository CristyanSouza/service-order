package br.com.so.ServiceOrder.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.so.ServiceOrder.services.exception.DuplicatedCpfException;
import br.com.so.ServiceOrder.services.exception.InvalidCpfException;
import br.com.so.ServiceOrder.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e){
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DuplicatedCpfException.class)
	public ResponseEntity<StandardError> duplicatedCpf(DuplicatedCpfException e){
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(InvalidCpfException.class)
	public ResponseEntity<StandardError> invalidCpf(InvalidCpfException e){
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}