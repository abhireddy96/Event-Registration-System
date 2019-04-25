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

import com.abhi.reddy.exceptions.EmployeeEventEnrollException;
import com.abhi.reddy.exceptions.EmployeeNotFoundException;
import com.abhi.reddy.exceptions.EventAlreadyExistsException;
import com.abhi.reddy.exceptions.EventNotFoundException;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;
import com.abhi.reddy.repository.EmployeeRepository;
import com.abhi.reddy.repository.EventRepository;;

@RestController
public class EventController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EventRepository eventRepository;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);


	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEvents() throws EventNotFoundException {
		LOGGER.info("GET - /event");
		List<Event> events = eventRepository.findAll();
		if (events.isEmpty()) {
			throw new EventNotFoundException("Event Table is empty");
		}
		return ResponseEntity.ok().body(events);
	}


	@PostMapping("/event")
	public ResponseEntity<?> addEvent(@RequestBody Event event) throws EventAlreadyExistsException {
		LOGGER.info("POST - /event");
		try {
			Event eventId = eventRepository.save(event);
			return ResponseEntity.ok().body("New Event has been saved with ID:" + eventId);
		} catch (DataIntegrityViolationException e) {
			throw new EventAlreadyExistsException("Event Already Exists with id: " + event.getId());
		} catch (Exception e) {
			throw new EventAlreadyExistsException("Exception: " + event.getId());
		}
	}


	@GetMapping("/event/{eventId}")
	public ResponseEntity<Event> getEvent(@PathVariable("eventId") int eventId) throws EventNotFoundException {
		LOGGER.info("GET - /event/{}", eventId);
		Event event = eventRepository.findByEid(eventId);
		if (event == null) {
			throw new EventNotFoundException("Event with Id: " + eventId + " doesn't exist");
		}
		return ResponseEntity.ok().body(event);
	}



	@DeleteMapping("/event/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable("eventId") int eventId) throws EventNotFoundException {
		LOGGER.info("DELETE - /event/{}", eventId);
		if(eventRepository.deleteByEid(eventId)==1)
			return ResponseEntity.ok().body("Event has been deleted successfully.");
		else
			throw new EventNotFoundException("Event with Id: " + eventId + " was already deleted");

	}


	@PostMapping("/event/{eventId}/employee/{employeeId}")
	public ResponseEntity<?> enrollEventToEmployee(@PathVariable("employeeId") String employeeId,
			@PathVariable("eventId") int eventId) throws EmployeeEventEnrollException {
		LOGGER.info("POST - /event/{}/employee/{}", eventId, employeeId);

		if(employeeRepository.existsByMid(employeeId)&&eventRepository.existsByEid(eventId)) {
			Event event = eventRepository.findByEid(eventId);
			List<String> employee = event.getEmployees();
			employee.add(employeeId);
			event.setEmployees(employee);;
			eventRepository.save(event);
			return ResponseEntity.ok()
					.body("Employee with id:" + employeeId + " has been successfuly enrolled with event id:" + eventId);
		}
		else 
			throw new EmployeeEventEnrollException(
					"Sorry couldn't process your request.EmployeeId or EventId doesn't exist.");

	}

	/**
	 * Gets the employees of event.
	 *
	 * @param eventId
	 *            the event id
	 * @return the employees of event
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 * @throws EventNotFoundException
	 *             the event not found exception
	 */
	@GetMapping("/event/{eventId}/employee")
	public ResponseEntity<List<Employee>> getEmployeesOfEvent(@PathVariable("eventId") int eventId)
			throws EmployeeNotFoundException, EventNotFoundException {
		LOGGER.info("GET - /event/{}/employee", eventId);
		try {
			List<String> employeeIds=eventRepository.findByEid(eventId).getEmployees();
			List<Employee> employee = employeeRepository.findAllByMid(employeeIds);

			if (employee.isEmpty()) 
				throw new EmployeeNotFoundException("No employees are available for Event with Id: " + eventId);

			return ResponseEntity.ok().body(employee);
		} catch (NullPointerException e) {
			throw new EventNotFoundException("Event with Id: " + eventId + " doesn't exist");
		}
	}

}