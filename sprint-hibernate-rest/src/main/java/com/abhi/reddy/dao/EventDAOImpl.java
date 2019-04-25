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
public class EventDAOImpl implements EventDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventDAOImpl.class);

	@Override
	public int addEvent(Event event) {
		int eventId=(int) sessionFactory.getCurrentSession().save(event);
		LOGGER.info("Inserted new Event[{}] with id:{}",event.toString(),eventId);
		return eventId;
	}

	@Override
	public List<Event> getEvents() {
	   @SuppressWarnings("unchecked")
	   List<Event> events= sessionFactory.getCurrentSession().createQuery("from Event").list();
	   LOGGER.info("Fetched All Event Details");
	   return events;
	}

	@Override
	@Transactional
	public void deleteEvent(int eventId) {
		Event event = (Event) sessionFactory.getCurrentSession().load(Event.class, eventId);
		if (null != event) {
			this.sessionFactory.getCurrentSession().delete(event);
			LOGGER.info("Deleted Event with ID: {}",eventId);
		}
	}

	@Override
	public Event updateEvent(Event event) {
		sessionFactory.getCurrentSession().saveOrUpdate(event);
		LOGGER.info("Updated Event[{}]",event.toString());
		return event;
	}

	@Override
	public Event getEvent(int eventId) {
		Event event =  (Event) sessionFactory.getCurrentSession().get(Event.class, eventId);
		LOGGER.info("Fetched Event Details for ID: {}",eventId);
		return event;
	}

	@Override
	@Transactional
	public void enrollEvent(String employeeId, int eventId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		Event event = (Event) sessionFactory.getCurrentSession().get(Event.class, eventId);
		event.addEmployee(employee);
		employee.addEvent(event);
		sessionFactory.getCurrentSession().save(event);	
		LOGGER.info("Enrolled Employee ID: {} with Event ID: {}",employeeId,eventId);
	}
	
	@Override
	@Transactional
	public List<Employee> getEmployeesOfEvent(int eventId) {
		Event event = (Event) sessionFactory.getCurrentSession().get(Event.class, eventId);
		LOGGER.info("Fetched Employee details for Event ID: {}",eventId);
		return event.getEmployees();
	}
	
}
