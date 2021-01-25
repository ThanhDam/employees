package com.employee.model;

import java.io.Serializable;

public class TeamMemberId implements Serializable{
	private String teamId;
	private String employeeId;
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public TeamMemberId() {
		
	}
	public TeamMemberId(String teamId, String employeeId) {
		this.teamId = teamId;
		this.employeeId = employeeId;
	}
}
