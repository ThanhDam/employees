package com.employee.model;

import java.util.Date;

public class EmployeeRole extends EmployeeRoot {
	private String role;

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public EmployeeRole() {
		
	}
	
	public EmployeeRole(String id, String lastName, String firstName, Date dob, String role) {
		super(id, lastName, firstName, dob);
		this.role = role;
	}
}
