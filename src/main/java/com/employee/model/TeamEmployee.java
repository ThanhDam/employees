package com.employee.model;

import java.util.Date;

public class TeamEmployee extends EmployeeRoot{
	private String team;

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public TeamEmployee() {
	}

	public TeamEmployee(String id, String lastName, String firstName, Date dob, String team) {
		super(id, lastName, firstName, dob);
		this.team = team;
	}

	
	
}
