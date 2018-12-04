package com.abhi.reddy.dao;

import java.util.List;

import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

/**
 * The Interface EmployeeDAO.
 */
public interface EmployeeDAO {

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the string
	 */
	public String addEmployee(Employee employee);

	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 */
	public void deleteEmployee(String employeeId);

	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	public Employee updateEmployee(Employee employee);

	/**
	 * Enroll employee.
	 *
	 * @param employeeId the employee id
	 * @param eventId the event id
	 */
	public void enrollEmployee(String employeeId, int eventId);

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public List<Employee> getEmployees();

	/**
	 * Gets the employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee
	 */
	public Employee getEmployee(String employeeId);

	/**
	 * Gets the events of employee.
	 *
	 * @param employeeId the employee id
	 * @return the events of employee
	 */
	public List<Event> getEventsOfEmployee(String employeeId) ;
}
