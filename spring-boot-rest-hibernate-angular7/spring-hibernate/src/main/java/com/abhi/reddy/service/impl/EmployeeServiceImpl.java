package com.abhi.reddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.reddy.dao.EmployeeDAO;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;
import com.abhi.reddy.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the string
	 */
	@Override
	public String addEmployee(Employee employee) {
		return  employeeDAO.addEmployee(employee);	
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	@Override
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}


	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}

	/**
	 * Gets the employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee
	 */
	@Override
	public Employee getEmployee(String employeeId) {
		return employeeDAO.getEmployee(employeeId);
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	@Override
	public void enrollEmployee(String employeeId, int eventId) {
		employeeDAO.enrollEmployee(employeeId,eventId); 	
	}

	/**
	 * Gets the events of employee.
	 *
	 * @param employeeId the employee id
	 * @return the events of employee
	 */
	@Override
	public List<Event> getEventsOfEmployee(String employeeId){
		return employeeDAO.getEventsOfEmployee(employeeId);	
	}

	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 */
	@Override
	public void deleteEmployee(String employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}

}
