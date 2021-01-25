package com.employee.model;

import java.util.Date;

public class EmployeeRoot {
	private String id;
	private String lastName;
	private String firstName;
	private Date dob;
	public EmployeeRoot() {
		
	}
	public EmployeeRoot(String id, String lastName, String firstName, Date dob) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dob = dob;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
}
