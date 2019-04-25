package com.abhi.reddy.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "events")
public class Event {

	/** The id. */
	@Id
	private String id;
	
	private int eid;

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The employees. */
	@JsonIgnore
	private List<String> employees;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public void setEmployees(List<String> employees) {
		this.employees = employees;
	}

	/**
	 * Instantiates a new event.
	 */
	public Event() {

	}

	/**
	 * Instantiates a new event.
	 *
	 * @param id the id
	 * @param title the title
	 * @param description the description
	 * @param employees the employees
	 */
	public Event(String id, int eid, String title, String description, List<String> employees) {
		super();
		this.id = id;
		this.eid = eid;
		this.title = title;
		this.description = description;
		this.employees = employees;
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public List<String> getEmployees() {
		return employees;
	}

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 */
	public void addEmployee(String employee) {
		this.employees.add(employee);
	}

	/**
	 * Override and implement toString method.
	 */
	@Override
	public String toString() {
		return "Events [id=" + id + ", title=" + title + ", description=" + description + ", employees=" + employees
				+ "]";
	}

	/**
	 * Override and implement hashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eid;
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
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
