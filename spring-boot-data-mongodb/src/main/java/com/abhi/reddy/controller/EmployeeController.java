package com.abhi.reddy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.abhi.reddy.repository.EmployeeRepository;
import com.abhi.reddy.repository.EventRepository;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EventRepository eventRepository;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);


	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeNotFoundException {
		LOGGER.info("GET - /employee");
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Table is empty");
		}
		return ResponseEntity.ok().body(employees);
	}

	@PostMapping("/employee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
		LOGGER.info("POST - /employee");
		try {
			Employee employeeId = employeeRepository.save(employee);
			return ResponseEntity.ok().body("New Employee has been saved with ID:" + employeeId);
		} catch (DataIntegrityViolationException e) {
			throw new EmployeeAlreadyExistsException("Employee Already Exists with id: " + employee.getId());
		}
	}

	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId)
			throws EmployeeNotFoundException {
		LOGGER.info("GET - /employee/{}", employeeId);
		Employee employee = employeeRepository.findByMid(employeeId);;
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with Id: " + employeeId + " doesn't exist");
		}
		return ResponseEntity.ok().body(employee);
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") String employeeId)
			throws EmployeeNotFoundException {
		LOGGER.info("DELETE - /employee/{}", employeeId);
	
			if(employeeRepository.deleteByMid(employeeId)==1)
				return ResponseEntity.ok().body("Employee has been deleted successfully.");
			else
			   throw new EmployeeNotFoundException("Employee with Id: " + employeeId + " was already deleted");
	}
	
	@PostMapping("/employee/{employeeId}/event/{eventId}")
	public ResponseEntity<?> enrollEmployeeToEvent(@PathVariable("employeeId") String employeeId,
			@PathVariable("eventId") int eventId) throws EmployeeEventEnrollException {
		LOGGER.info("POST - /employee/{}/event/{}", employeeId, eventId);
		try {
			Employee employee = employeeRepository.findByMid(employeeId);
			List<Integer> event = employee.getEvents();
			event.add(eventId);
			employee.setEvents(event);;
			employeeRepository.save(employee);
			return ResponseEntity.ok()
					.body("Employee with id:" + employeeId + " has been successfuly enrolled with event id:" + eventId);
		} catch (NullPointerException e) {
			throw new EmployeeEventEnrollException(
					"Sorry couldn't process your request.EmployeeId or EventId doesn't exist.");
		}
	}


	@GetMapping("/employee/{employeeId}/event")
	public ResponseEntity<List<Event>> getEventsOfEmployee(@PathVariable("employeeId") String employeeId)
			throws EventNotFoundException, EmployeeNotFoundException {
		LOGGER.info("GET - /employee/{}/event", employeeId);
		try {
			List<Integer> eventIds=employeeRepository.findByMid(employeeId).getEvents();
			List<Event> events = eventRepository.findAllByEid(eventIds);
					
			if (events.isEmpty()) {
				throw new EventNotFoundException("No events are available for Employee with Id: " + employeeId);
			}
			return ResponseEntity.ok().body(events);
		} catch (NullPointerException e) {
			throw new EmployeeNotFoundException("Employee with Id: " + employeeId + " doesn't exist");
		}
	}



}