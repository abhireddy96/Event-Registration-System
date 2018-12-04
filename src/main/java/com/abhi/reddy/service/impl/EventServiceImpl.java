package com.abhi.reddy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.reddy.dao.EventDAO;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;
import com.abhi.reddy.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDAO eventDAO;

	/**
	 * Adds the event.
	 *
	 * @param event the event
	 * @return the event
	 */
	@Override
	public int addEvent(Event event) {
		return  eventDAO.addEvent(event);
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	@Override
	public List<Event> getEvents() {
		return eventDAO.getEvents();
	}


	/**
	 * Update event.
	 *
	 * @param event the event
	 * @return the event
	 */
	@Override
	public Event updateEvent(Event event) {
		return eventDAO.updateEvent(event);
	}

	/**
	 * Gets the event.
	 *
	 * @param eventId the event id
	 * @return the event
	 */
	@Override
	public Event getEvent(int eventId) {
		return eventDAO.getEvent(eventId);
	}
		

	/**
	 * Delete event.
	 *
	 * @param eventId the event id
	 */
	@Override
	public void deleteEvent(int eventId) {
		eventDAO.deleteEvent(eventId);
		
	}
	
	/**
	 * Enroll event.
	 *
	 * @param employeeId the employee id
	 * @param eventId the event id
	 */
	@Override
	public void enrollEvent(String employeeId, int eventId) {
		eventDAO.enrollEvent(employeeId,eventId);
	  	
	}
	
	/**
	 * Gets the employees of event.
	 *
	 * @param eventId the event id
	 * @return the employees of event
	 */
	@Override
	public List<Employee> getEmployeesOfEvent(int eventId){
		return eventDAO.getEmployeesOfEvent(eventId);
		
	}

}
