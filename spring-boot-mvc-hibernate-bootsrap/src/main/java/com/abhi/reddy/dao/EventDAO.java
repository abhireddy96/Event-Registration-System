package com.abhi.reddy.dao;

import java.util.List;

import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

/**
 * The Interface EventDAO.
 */
public interface EventDAO {

	/**
	 * Adds the event.
	 *
	 * @param event the event
	 * @return the event
	 */
	public int addEvent(Event event);

	/**
	 * Delete event.
	 *
	 * @param eventId the event id
	 */
	public void deleteEvent(int eventId);

	/**
	 * Update event.
	 *
	 * @param event the event
	 * @return the event
	 */
	public Event updateEvent(Event event);

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<Event> getEvents();

	/**
	 * Gets the event.
	 *
	 * @param eventId the event id
	 * @return the event
	 */
	public Event getEvent(int eventId);

	/**
	 * Enroll event.
	 *
	 * @param employeeId the employee id
	 * @param eventId the event id
	 */
	public void enrollEvent(String employeeId, int eventId);

	/**
	 * Gets the employees of event.
	 *
	 * @param eventId the event id
	 * @return the employees of event
	 */
	public List<Employee> getEmployeesOfEvent(int eventId);

}
