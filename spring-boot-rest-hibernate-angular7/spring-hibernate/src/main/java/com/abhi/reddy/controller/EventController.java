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

import com.abhi.reddy.exceptions.EmployeeEventEnrollException;
import com.abhi.reddy.exceptions.EmployeeNotFoundException;
import com.abhi.reddy.exceptions.EventAlreadyExistsException;
import com.abhi.reddy.exceptions.EventNotFoundException;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;
import com.abhi.reddy.service.EventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;;

@RestController
@Api(tags = "Event Interface")
public class EventController {

	/** The event service. */
	@Autowired
	private EventService eventService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	/**
	 * Gets the all events.
	 *
	 * @return the all events
	 * @throws EventNotFoundException
	 *             the event not found exception
	 */
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of all events")
	public ResponseEntity<List<Event>> getAllEvents() throws EventNotFoundException {
		LOGGER.info("GET - /event");
		List<Event> events = eventService.getEvents();
		if (events.isEmpty()) {
			throw new EventNotFoundException("Event Table is empty");
		}
		return ResponseEntity.ok().body(events);
	}

	/**
	 * Adds the event.
	 *
	 * @param event
	 *            the event
	 * @return the response entity
	 * @throws EventAlreadyExistsException
	 *             the event already exists exception
	 */
	@PostMapping("/event")
	@ApiOperation(value = "Add a new Event")
	public ResponseEntity<?> addEvent(@RequestBody Event event) throws EventAlreadyExistsException {
		LOGGER.info("POST - /event");
		try {
			int eventId = eventService.addEvent(event);
			return ResponseEntity.ok().body("New Event has been saved with ID:" + eventId);
		} catch (DataIntegrityViolationException e) {
			throw new EventAlreadyExistsException("Event Already Exists with id: " + event.getId());
		} catch (Exception e) {
			throw new EventAlreadyExistsException("Exception: " + event.getId());
		}
	}

	/**
	 * Gets the event.
	 *
	 * @param eventId
	 *            the event id
	 * @return the event
	 * @throws EventNotFoundException
	 *             the event not found exception
	 */
	@GetMapping("/event/{eventId}")
	@ApiOperation(value = "Get details of a Event by ID")
	public ResponseEntity<Event> getEvent(@PathVariable("eventId") int eventId) throws EventNotFoundException {
		LOGGER.info("GET - /event/{}", eventId);
		Event event = eventService.getEvent(eventId);
		if (event == null) {
			throw new EventNotFoundException("Event with Id: " + eventId + " doesn't exist");
		}
		return ResponseEntity.ok().body(event);
	}

	/**
	 * Update event.
	 *
	 * @param eventId
	 *            the event id
	 * @param event
	 *            the event
	 * @return the response entity
	 * @throws EventNotFoundException
	 *             the event not found exception
	 */
	@PutMapping("/event/{eventId}")
	@ApiOperation(value = "Update deatails of Event")
	public ResponseEntity<?> updateEvent(@PathVariable("eventId") int eventId, @RequestBody Event event)
			throws EventNotFoundException {
		LOGGER.info("PUT - /event/{}", eventId);
		try {
			event.setId(eventId);
			eventService.updateEvent(event);
			return ResponseEntity.ok().body("Event has been updated successfully.");
		} catch (HibernateOptimisticLockingFailureException e) {
			throw new EventNotFoundException(
					"Couldn't update event because Event with Id: " + eventId + " doesn't exist");
		}
	}

	/**
	 * Delete event.
	 *
	 * @param eventId
	 *            the event id
	 * @return the response entity
	 * @throws EventNotFoundException
	 *             the event not found exception
	 */
	@DeleteMapping("/event/{eventId}")
	@ApiOperation(value = "Delete Event By ID")
	public ResponseEntity<?> deleteEvent(@PathVariable("eventId") int eventId) throws EventNotFoundException {
		LOGGER.info("DELETE - /event/{}", eventId);
		try {
			eventService.deleteEvent(eventId);
			return ResponseEntity.ok().body("Event has been deleted successfully.");
		} catch (Exception e) {
			throw new EventNotFoundException("Event with Id: " + eventId + " was already deleted");
		}
	}

	/**
	 * Enroll event to employee.
	 *
	 * @param employeeId
	 *            the employee id
	 * @param eventId
	 *            the event id
	 * @return the response entity
	 * @throws EmployeeEventEnrollException
	 *             the employee event enroll exception
	 */
	@PostMapping("/event/{eventId}/employee/{employeeId}")
	@ApiOperation(value = "Enroll Employee to a Event")
	public ResponseEntity<?> enrollEventToEmployee(@PathVariable("employeeId") String employeeId,
			@PathVariable("eventId") int eventId) throws EmployeeEventEnrollException {
		LOGGER.info("POST - /event/{}/employee/{}", eventId, employeeId);
		try {
			eventService.enrollEvent(employeeId, eventId);
			return ResponseEntity.ok()
					.body("Employee with id:" + employeeId + " has been successfuly enrolled with event id:" + eventId);
		} catch (NullPointerException e) {
			throw new EmployeeEventEnrollException(
					"Sorry couldn't process your request.EmployeeId or EventId doesn't exist.");
		}
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
	@ApiOperation(value = "Get list of employees enrolled in a Event")
	public ResponseEntity<List<Employee>> getEmployeesOfEvent(@PathVariable("eventId") int eventId)
			throws EmployeeNotFoundException, EventNotFoundException {
		LOGGER.info("GET - /event/{}/employee", eventId);
		try {
			List<Employee> employee = eventService.getEmployeesOfEvent(eventId);
			if (employee.isEmpty()) {
				throw new EmployeeNotFoundException("No employees are available for Event with Id: " + eventId);
			}
			return ResponseEntity.ok().body(employee);
		} catch (NullPointerException e) {
			throw new EventNotFoundException("Event with Id: " + eventId + " doesn't exist");
		}
	}

}