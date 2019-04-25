package com.abhi.reddy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel
public class Event {

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENT_ID")
	@ApiModelProperty(notes = "The database generated Event ID")
	private int id;

	/** The title. */
	@Column(name="EVENT_TITLE")
	@ApiModelProperty(notes = "Title of Event")
	private String title;

	/** The description. */
	@Column
	@ApiModelProperty(notes = "Description of Event")
	private String description;

	/** The employees. */
	@ManyToMany(mappedBy="events",fetch=FetchType.LAZY)
	@Cascade( {
		CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.REFRESH,
		CascadeType.PERSIST})
	@JsonIgnore
	private List<Employee> employees;

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
	public Event(int id, String title, String description, List<Employee> employees) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.employees = employees;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
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
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 */
	public void addEmployee(Employee employee) {
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
		result = prime * result + id;
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
