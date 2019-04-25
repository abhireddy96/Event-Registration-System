package com.abhi.reddy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abhi.reddy.exceptions.EmployeeAlreadyExistsException;
import com.abhi.reddy.exceptions.EmployeeEventEnrollException;
import com.abhi.reddy.exceptions.EmployeeNotFoundException;
import com.abhi.reddy.exceptions.EventAlreadyExistsException;
import com.abhi.reddy.exceptions.EventNotFoundException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(EmployeeAlreadyExistsException.class) 
	public ResponseEntity <?> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }
	
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }
	
	@ExceptionHandler({EmployeeEventEnrollException.class})
	public ResponseEntity<?> handleEmployeeEventEnrollException(EmployeeEventEnrollException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }
	
	@ExceptionHandler({EventNotFoundException.class})
	public ResponseEntity<?> handleEventNotFoundException(EventNotFoundException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }
	
	@ExceptionHandler(EventAlreadyExistsException.class) 
	public ResponseEntity <?> handleEventAlreadyExistsException(EventAlreadyExistsException ex) {
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
    }
}
