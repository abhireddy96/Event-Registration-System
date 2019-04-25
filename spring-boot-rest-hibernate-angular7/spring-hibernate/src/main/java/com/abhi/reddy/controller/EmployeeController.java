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
import com.abhi.reddy.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;;

@RestController
@Api(tags = "Employee Interface")
public class EmployeeController {

	/** The employee service. */
	@Autowired
	private EmployeeService employeeService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 */

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of all employees")
	public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeNotFoundException {
		LOGGER.info("GET - /employee");
		List<Employee> employees = employeeService.getEmployees();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Table is empty");
		}
		return ResponseEntity.ok().body(employees);
	}

	/**
	 * Add a employee.
	 *
	 * @param employee
	 *            the employee
	 * @return the response entity
	 * @throws EmployeeAlreadyExistsException
	 *             the employee already exists exception
	 */
	@PostMapping("/employee")
	@ApiOperation(value = "Add new Employee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws EmployeeAlreadyExistsException {
		LOGGER.info("POST - /employee");
		try {
			String employeeId = employeeService.addEmployee(employee);
			return ResponseEntity.ok().body("New Employee has been saved with ID:" + employeeId);
		} catch (DataIntegrityViolationException e) {
			throw new EmployeeAlreadyExistsException("Employee Already Exists with id: " + employee.getId());
		}

	}

	/**
	 * Gets the employee by id.
	 *
	 * @param employeeId
	 *            the employee id
	 * @return the employee by id
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 */
	@GetMapping("/employee/{employeeId}")
	@ApiOperation(value = "Get Employee details By ID")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") String employeeId)
			throws EmployeeNotFoundException {
		LOGGER.info("GET - /employee/{}", employeeId);
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with Id: " + employeeId + " doesn't exist");
		}
		return ResponseEntity.ok().body(employee);
	}

	/**
	 * Update employee.
	 *
	 * @param employeeId
	 *            the employee id
	 * @param employee
	 *            the employee
	 * @return the response entity
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 */
	@PutMapping("/employee/{employeeId}")
	@ApiOperation(value = "Update Details of Employee")
	public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") String employeeId,
			@RequestBody Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("PUT - /employee/{}", employeeId);
		try {
			employee.setId(employeeId);
			employeeService.updateEmployee(employee);
			return ResponseEntity.ok().body("Employee has been updated successfully.");
		} catch (HibernateOptimisticLockingFailureException e) {
			throw new EmployeeNotFoundException(
					"Couldn't update employee because Employee with Id: " + employeeId + " doesn't exist");
		}
	}

	/**
	 * Delete employee.
	 *
	 * @param employeeId
	 *            the employee id
	 * @return the response entity
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 */
	@DeleteMapping("/employee/{employeeId}")
	@ApiOperation(value = "Delete Employee By ID")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") String employeeId)
			throws EmployeeNotFoundException {
		LOGGER.info("DELETE - /employee/{}", employeeId);
		try {
			employeeService.deleteEmployee(employeeId);
			return ResponseEntity.ok().body("Employee has been deleted successfully.");
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Employee with Id: " + employeeId + " was already deleted");
		}
	}

	/**
	 * Enroll employee to event.
	 *
	 * @param employeeId
	 *            the employee id
	 * @param eventId
	 *            the event id
	 * @return the response entity
	 * @throws EmployeeEventEnrollException
	 *             the employee event enroll exception
	 */
	@PostMapping("/employee/{employeeId}/event/{eventId}")
	@ApiOperation(value = "Enroll Employee to a Event")
	public ResponseEntity<?> enrollEmployeeToEvent(@PathVariable("employeeId") String employeeId,
			@PathVariable("eventId") int eventId) throws EmployeeEventEnrollException {
		LOGGER.info("POST - /employee/{}/event/{}", employeeId, eventId);
		try {
			employeeService.enrollEmployee(employeeId, eventId);
			return ResponseEntity.ok()
					.body("Employee with id:" + employeeId + " has been successfuly enrolled with event id:" + eventId);
		} catch (NullPointerException e) {
			throw new EmployeeEventEnrollException(
					"Sorry couldn't process your request.EmployeeId or EventId doesn't exist.");
		}
	}

	/**
	 * Gets the events of employee.
	 *
	 * @param employeeId
	 *            the employee id
	 * @return the events of employee
	 * @throws EventNotFoundException
	 *             the event not found exception
	 * @throws EmployeeNotFoundException
	 *             the employee not found exception
	 */
	@GetMapping("/employee/{employeeId}/event")
	@ApiOperation(value = "Get list of events enrolled of a Employee")
	public ResponseEntity<List<Event>> getEventsOfEmployee(@PathVariable("employeeId") String employeeId)
			throws EventNotFoundException, EmployeeNotFoundException {
		LOGGER.info("GET - /employee/{}/event", employeeId);
		try {
			List<Event> events = employeeService.getEventsOfEmployee(employeeId);
			if (events.isEmpty()) {
				throw new EventNotFoundException("No events are available for Employee with Id: " + employeeId);
			}
			return ResponseEntity.ok().body(events);
		} catch (NullPointerException e) {
			throw new EmployeeNotFoundException("Employee with Id: " + employeeId + " doesn't exist");
		}
	}
}