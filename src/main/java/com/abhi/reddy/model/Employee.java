package com.abhi.reddy.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Class Employee.
 */
@Entity
@ApiModel
public class Employee {

	/** The id. */
	@Id
	@Column(name="MID")
	@ApiModelProperty(notes = "Employee ID provided by organization")
	private String id;

	/** The name. */
	@Column(name="NAME")
	@ApiModelProperty(notes = "Full Name of Employee")
	private String name;

	/** The join date. */
	@Column(name="JOIN_DATE")
	@ApiModelProperty(notes = "Joining Date of Employee in YYYY-MM-DD format")
	@Temporal(TemporalType.DATE)
	private Date joinDate;

	/** The email ID. */
	@Column(name="EMAIL_ID")
	@ApiModelProperty(notes = "Email ID of Employee provided by organization")
	private String emailID;

	/** The events. */
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="EMPLOYEE_EVENT",
	joinColumns=@JoinColumn(name="MID_FK"),
	inverseJoinColumns=@JoinColumn(name="EVENT_ID_FK"))
	@Cascade( {
		CascadeType.DETACH,
		CascadeType.MERGE,
		CascadeType.REFRESH,
		CascadeType.PERSIST})
	@JsonIgnore
	private List<Event> events;

	/**
	 * Instantiates a new employee.
	 */
	public Employee() {

	}

	/**
	 * Instantiates a new employee.
	 *
	 * @param id the id
	 * @param name the name
	 * @param joinDate the join date
	 * @param emailID the email ID
	 * @param events the events
	 */
	public Employee(String id, String name, Date joinDate, String emailID, List<Event> events) {
		super();
		this.id = id;
		this.name = name;
		this.joinDate = joinDate;
		this.emailID = emailID;
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
	public List<Event> getEvents() {
		return events;
	}

	/**
	 * Adds the event.
	 *
	 * @param event the event
	 */
	public void addEvent(Event event) {
		this.events.add(event);
	}


	/**
	 * Override and implement toString method.
	 */
	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", emailID=" + emailID
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
