package com.abhi.reddy.service;

import java.util.List;

import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

public interface EmployeeService {

public String addEmployee(Employee employee);
	
	public void deleteEmployee(String employeeId);
	
	public Employee updateEmployee(Employee employee);
	
	public void enrollEmployee(String employeeId, int eventId);

	public List<Employee> getEmployees();
	
	public Employee getEmployee(String employeeId);

	public List<Event> getEventsOfEmployee(String employeeId) ;
}
