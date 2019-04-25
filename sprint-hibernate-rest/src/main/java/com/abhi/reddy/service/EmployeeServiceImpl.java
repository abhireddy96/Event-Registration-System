package com.abhi.reddy.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.reddy.dao.EmployeeDAO;
import com.abhi.reddy.model.Event;
import com.abhi.reddy.model.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public String addEmployee(Employee employee) {
		try {
		return  employeeDAO.addEmployee(employee);	
	}catch(ConstraintViolationException | DataIntegrityViolationException ex) {
		return "Id should be unique";
	}
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}


	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}

	@Override
	public Employee getEmployee(String employeeId) {
		return employeeDAO.getEmployee(employeeId);
	}

	@Override
	public void enrollEmployee(String employeeId, int eventId) {
		employeeDAO.enrollEmployee(employeeId,eventId); 	
	}
	
	@Override
	@Transactional
	public List<Event> getEventsOfEmployee(String employeeId){
		List<Event> events= employeeDAO.getEventsOfEmployee(employeeId);	
		return events;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}

}
