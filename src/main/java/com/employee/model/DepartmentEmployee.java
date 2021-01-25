package com.employee.model;

import java.util.Date;

public class DepartmentEmployee extends EmployeeRoot{
	private String department;
	
	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public DepartmentEmployee() {
		
	}

	public DepartmentEmployee(String id, String lastName, String firstName, Date dob, String department) {
		super(id, lastName, firstName, dob);
		this.department = department;
	}
}
