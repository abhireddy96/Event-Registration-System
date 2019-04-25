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

@Entity
public class Employee {
	
	@Id
	@Column(name="MID")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="JOIN_DATE")
	@Temporal(TemporalType.DATE)
	//@PastOrPresent(message = "Joining Date should be Past or Presesnt")
	private Date joinDate;
	
	@Column(name="EMAIL_ID")
	//@Email(message = "Email should be valid")
	private String emailID;
	
	@ManyToMany(fetch=FetchType.EAGER)
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
	
    public Employee() {
		
	}

	public Employee(String id, String name, Date joinDate, String emailID, List<Event> events) {
		super();
		this.id = id;
		this.name = name;
		this.joinDate = joinDate;
		this.emailID = emailID;
		this.events = events;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void addEvent(Event event) {
		this.events.add(event);
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", emailID=" + emailID
				+ ", events=" + events + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

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
