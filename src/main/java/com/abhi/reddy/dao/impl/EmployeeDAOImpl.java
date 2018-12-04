package com.abhi.reddy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhi.reddy.dao.EmployeeDAO;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EntityManager em;

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the string
	 */
	@Override
	public String addEmployee(Employee employee) {
		em.persist(employee);
		return employee.getId();		
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	@Override
	public List<Employee> getEmployees() {	
		return em.createQuery("Select e From Employee e",
				Employee.class).getResultList();
	}


	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 */
	@Override
	@Transactional
	public void deleteEmployee(String employeeId) {
		Employee employee = em.find(Employee.class,employeeId);

		if(employee != null){
			em.remove(employee);
		}
	}

	/**
	 * Enroll employee.
	 *
	 * @param employeeId the employee id
	 * @param eventId the event id
	 */
	@Override
	public Employee updateEmployee(Employee employee) {
		em.merge(employee);
		return employee;
	}

	/**
	 * Gets the employee.
	 *
	 * @param employeeId the employee id
	 * @return the employee
	 */
	@Override
	public Employee getEmployee(String employeeId) {
		Employee employee = em.find(Employee.class,employeeId);
		return employee;
	}

	/**
	 * Enroll employee.
	 *
	 * @param employeeId the employee id
	 * @param eventId the event id
	 */
	@Override
	@Transactional
	public void enrollEmployee(String employeeId, int eventId) {
		Employee employee = em.find(Employee.class,employeeId);
		Event event = em.find(Event.class,eventId);
		event.addEmployee(employee);
		employee.addEvent(event);
		em.persist(employee);
	}

	/**
	 * Gets the events of employee.
	 *
	 * @param employeeId the employee id
	 * @return the events of employee
	 */
	@Override
	@Transactional
	public List<Event> getEventsOfEmployee(String employeeId) {
		Employee employee = em.find(Employee.class,employeeId);
		return employee.getEvents();
	}

}
