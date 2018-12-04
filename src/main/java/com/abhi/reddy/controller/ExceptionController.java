package com.abhi.reddy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abhi.reddy.exceptions.EmployeeAlreadyExistsException;
import com.abhi.reddy.exceptions.EmployeeEventEnrollException;
import com.abhi.reddy.exceptions.EmployeeNotFoundException;
import com.abhi.reddy.exceptions.EventAlreadyExistsException;
import com.abhi.reddy.exceptions.EventNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;

@ControllerAdvice
public class ExceptionController {

	/**
	 * Handle employee already exists exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(EmployeeAlreadyExistsException.class) 
	public ResponseEntity <?> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException ex) {
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}

	/**
	 * Handle employee not found exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}

	/**
	 * Handle employee event enroll exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler({EmployeeEventEnrollException.class})
	public ResponseEntity<?> handleEmployeeEventEnrollException(EmployeeEventEnrollException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}

	/**
	 * Handle event not found exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler({EventNotFoundException.class})
	public ResponseEntity<?> handleEventNotFoundException(EventNotFoundException ex){
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}

	/**
	 * Handle event already exists exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(EventAlreadyExistsException.class) 
	public ResponseEntity <?> handleEventAlreadyExistsException(EventAlreadyExistsException ex) {
		return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
	}

	/**
	 * Handle invalid json input exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(JsonParseException.class) 
	public ResponseEntity <?> handleInvalidInputException(JsonParseException ex) {
		return ResponseEntity.badRequest().body("Provide Input Data In Proper Format\nJson Input Is Invalid");
	}
}
