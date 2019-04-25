package com.abhi.reddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhi.reddy.dao.EventDAO;
import com.abhi.reddy.model.Employee;
import com.abhi.reddy.model.Event;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDAO eventDAO;

	@Override
	public int addEvent(Event event) {
		return  eventDAO.addEvent(event);
	}

	@Override
	public List<Event> getEvents() {
		return eventDAO.getEvents();
	}


	@Override
	public Event updateEvent(Event event) {
		return eventDAO.updateEvent(event);
	}

	@Override
	public Event getEvent(int eventId) {
		return eventDAO.getEvent(eventId);
	}
		

	@Override
	public void deleteEvent(int eventId) {
		eventDAO.deleteEvent(eventId);
		
	}
	
	@Override
	public void enrollEvent(String employeeId, int eventId) {
		eventDAO.enrollEvent(employeeId,eventId);
	  	
	}
	
	@Override
	public List<Employee> getEmployeesOfEvent(int eventId){
		return eventDAO.getEmployeesOfEvent(eventId);
		
	}

}
