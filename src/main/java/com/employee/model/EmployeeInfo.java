package com.employee.model;

import java.util.Date;

public class EmployeeInfo extends EmployeeRole{
	private String team;
	private String department;
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public EmployeeInfo() {
		
	}
	public EmployeeInfo(String id, String lastName, String firstName, Date dob, String role, String team, String department) {
		super(id, lastName, firstName, dob, role);
		this.team = team;
		this.department = department;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
