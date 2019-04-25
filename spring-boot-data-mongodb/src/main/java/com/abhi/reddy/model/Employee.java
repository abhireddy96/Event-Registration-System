package com.abhi.reddy.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The Class Employee.
 */
@Document(collection = "employees")
public class Employee {

	/** The id. */
	@Id
	private String id;
	
	private String mid;

	/** The name. */
	private String name;

	/** The join date. */
	private Date joinDate;

	/** The email ID. */
	private String emailID;

	@JsonIgnore
	private List<Integer> events;

	/**
	 * Instantiates a new employee.
	 */
	public Employee() {

	}

	public Employee(String id, String mid, String name, Date joinDate, String emailID, List<Integer> events) {
		super();
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.joinDate = joinDate;
		this.emailID = emailID;
		this.events = events;
	}


	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setEvents(List<Integer> events) {
		this.events = events;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the join date.
	 *
	 * @return the join date
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * Sets the join date.
	 *
	 * @param joinDate the new join date
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * Gets the email ID.
	 *
	 * @return the email ID
	 */
	public String getEmailID() {
		return emailID;
	}

	/**
	 * Sets the email ID.
	 *
	 * @param emailID the new email ID
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/**
	 * Gets the events.
	 *
	 * @return the events
	 */
	public List<Integer> getEvents() {
		return events;
	}

	/**
	 * Adds the event.
	 *
	 * @param event the event
	 */
	public void addEvent(Integer event) {
		this.events.add(event);
	}


	/**
	 * Override and implement toString method.
	 */
	@Override
	public String toString() {
		return "Employees [id=" + id + ", mid=" + mid + ", name=" + name + ", joinDate=" + joinDate + ", emailID=" + emailID
				+ ", events=" + events + "]";
	}


	/**
	 * Override and implement hashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	/**
	 * Override and implement equals method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
