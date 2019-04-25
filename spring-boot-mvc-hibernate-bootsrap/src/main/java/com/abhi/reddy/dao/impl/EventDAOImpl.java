package com.abhi.reddy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhi.reddy.dao.EventDAO;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

@Repository
@Transactional
public class EventDAOImpl implements EventDAO {

	@Autowired
	private EntityManager em;

	/**
	 * Adds the event.
	 *
	 * @param event the event
	 * @return the event
	 */
	@Override
	public int addEvent(Event event) {
		em.persist(event);
		return event.getId();
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	@Override
	public List<Event> getEvents() {
		return em.createQuery("Select e From Event e",
				Event.class).getResultList();
	}

	/**
	 * Delete event.
	 *
	 * @param eventId the event id
	 */
	@Override
	@Transactional
	public void deleteEvent(int eventId) {
		Event event = em.find(Event.class,eventId);

		if(event != null){
			em.remove(event);
		}
	}

	/**
	 * Update event.
	 *
	 * @param event the event
	 * @return the event
	 */
	@Override
	public Event updateEvent(Event event) {
		em.merge(event);
		return event;
	}

	/**
	 * Gets the event.
	 *
	 * @param eventId the event id
	 * @return the event
	 */
	@Override
	public Event getEvent(int eventId) {
		Event event = em.find(Event.class,eventId);
		return event;
	}

	/**
	 * Enroll event.
	 *
	 * @param employeeId the employee id
	 * @param eventId the event id
	 */
	@Override
	@Transactional
	public void enrollEvent(String employeeId, int eventId) {
		Employee employee = em.find(Employee.class,employeeId);
		Event event = em.find(Event.class,eventId);
		event.addEmployee(employee);
		employee.addEvent(event);
		em.persist(event);
	}

	/**
	 * Gets the employees of event.
	 *
	 * @param eventId the event id
	 * @return the employees of event
	 */
	@Override
	@Transactional
	public List<Employee> getEmployeesOfEvent(int eventId) {
		Event event = em.find(Event.class,eventId);
		return event.getEmployees();
	}

}
