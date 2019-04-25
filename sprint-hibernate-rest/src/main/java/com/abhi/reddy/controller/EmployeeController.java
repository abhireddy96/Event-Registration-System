package com.abhi.reddy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.reddy.exceptions.EmployeeAlreadyExistsException;
import com.abhi.reddy.exceptions.EmployeeEventEnrollException;
import com.abhi.reddy.exceptions.EmployeeNotFoundException;
import com.abhi.reddy.exceptions.EventNotFoundException;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;
import com.abhi.reddy.service.EmployeeService;;

@RestController
//@Transactional
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	/*---get all employees---*/
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeNotFoundException {
		LOGGER.info("GET - /employee");
		List<Employee> employees = employeeService.getEmployees();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Table is empty");
		}
		return ResponseEntity.ok().body(employees);
	}

	/*---Add new employee---*/
	@PostMapping("/employee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
		LOGGER.info("POST - /employee");
		try {
		String employeeId = employeeService.addEmployee(employee);
		return ResponseEntity.ok().body("New Employee has been saved with ID:" + employeeId);
		}catch(DataIntegrityViolationException e) {
			throw new EmployeeAlreadyExistsException("Employee Already Exists with id: "+employee.getId());
		}
		
	}

	/*---Get a employee by id---*/
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId) throws  EmployeeNotFoundException{
		LOGGER.info("GET - /employee/{}",employeeId);
		Employee employee = employeeService.getEmployee(employeeId);
		if(employee==null) {
			throw new EmployeeNotFoundException("Employee with Id: "+employeeId+" doesn't exist");
		}
		return ResponseEntity.ok().body(employee);
	}


	/*---Update a employee by id---*/
	@PutMapping("/employee/{employeeId}")
	public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") String employeeId, @RequestBody Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("PUT - /employee/{}",employeeId);
		try {
		employee.setId(employeeId);
		employeeService.updateEmployee(employee);
		return ResponseEntity.ok().body("Employee has been updated successfully.");
		}catch(HibernateOptimisticLockingFailureException e) {
			throw new EmployeeNotFoundException("Couldn't update employee because Employee with Id: "+employeeId+" doesn't exist");
		}
	}

	/* ---Delete a employee by id---*/
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") String employeeId) throws EmployeeNotFoundException {
		LOGGER.info("DELETE - /employee/{}",employeeId);
		try {
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok().body("Employee has been deleted successfully.");
		}catch(Exception e) {
			throw new EmployeeNotFoundException("Employee with Id: "+employeeId+" was already deleted");
		}
	}

	/*---Enroll new employee to event ---*/
	@PostMapping("/employee/{employeeId}/event/{eventId}")
	public ResponseEntity<?> enrollEmployeeToEvent(@PathVariable("employeeId") String employeeId,@PathVariable("eventId") int eventId) throws EmployeeEventEnrollException {
		LOGGER.info("POST - /employee/{}/event/{}",employeeId,eventId);
		try {
		employeeService.enrollEmployee(employeeId, eventId);
		return ResponseEntity.ok().body("Employee with id:"+employeeId+" has been successfuly enrolled with event id:"+eventId);
		}catch(NullPointerException e) {
			throw new EmployeeEventEnrollException("Sorry couldn't process your request");
		}
	}

	/*---Get events of employee by id---*/
	@GetMapping("/employee/{employeeId}/event")
	public ResponseEntity<List<Event>> getEventsOfEmployee(@PathVariable("employeeId") String employeeId) throws EventNotFoundException, EmployeeNotFoundException {
		LOGGER.info("GET - /employee/{}/event",employeeId);
		try {
		List<Event> events = employeeService.getEventsOfEmployee(employeeId);
		if(events.isEmpty()) {
			throw new EventNotFoundException("No events are available for Employee with Id: "+employeeId);
		}
		return ResponseEntity.ok().body(events);
		}catch(NullPointerException e) {
			throw new EmployeeNotFoundException("Employee with Id: "+employeeId+" doesn't exist");
		}
	}
}