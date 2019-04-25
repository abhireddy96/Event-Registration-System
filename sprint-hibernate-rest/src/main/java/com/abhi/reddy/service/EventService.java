package com.abhi.reddy.service;

import java.util.List;

import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

public interface EventService {

public int addEvent(Event event);
	
	public void deleteEvent(int eventId);
	
	public Event updateEvent(Event event);

	public List<Event> getEvents();
	
	public Event getEvent(int eventId);

	void enrollEvent(String employeeId, int eventId);

	public List<Employee> getEmployeesOfEvent(int eventId);

}
