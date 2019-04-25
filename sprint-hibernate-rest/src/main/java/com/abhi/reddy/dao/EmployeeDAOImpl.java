package com.abhi.reddy.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	

	@Override
	public String addEmployee(Employee employee) {
		String id=(String) sessionFactory.getCurrentSession().save(employee);
		LOGGER.info("Inserted new Employee[{}] with id:{}",employee.toString(),id);
		return id;
	}

	
	@Override
	public List<Employee> getEmployees() {
		@SuppressWarnings("unchecked")
		List<Employee> employees = (List<Employee>) sessionFactory.getCurrentSession().createQuery("from Employee").list();
		LOGGER.info("Fetched All Employee Details");
		return employees;
	}

	@Override
	@Transactional
	public void deleteEmployee(String employeeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
			LOGGER.info("Deleted Employee with ID: {}",employeeId);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
		LOGGER.info("Updated Employee[{}]",employee.toString());
		return employee;
	}

	@Override
	public Employee getEmployee(String employeeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		LOGGER.info("Fetched Employee Details for ID: {}",employeeId);
		return employee;
	}

	@Override
	@Transactional
	public void enrollEmployee(String employeeId, int eventId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		Event event = (Event) sessionFactory.getCurrentSession().get(Event.class, eventId);
		event.addEmployee(employee);
		employee.addEvent(event);
		sessionFactory.getCurrentSession().save(employee);
		LOGGER.info("Enrolled Employee ID: {} with Event ID: {}",employeeId,eventId);
	}

	@Override
	@Transactional
	public List<Event> getEventsOfEmployee(String employeeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		LOGGER.info("Fetched Event details for Employee ID: {}",employeeId);
		return employee.getEvents();
	}

}
