package com.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TEAM_MEMBER_MANAGEMENT")
@IdClass(TeamMemberId.class)
public class TeamMemberManagement {
	@Id
	@Column(name= "team_id", nullable = false)
	private String teamId;
	@Id
	@Column(name= "employee_id", nullable = false)
	private String employeeId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "team_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Team team;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Employee employee;
	
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

	public TeamMemberManagement(String teamId, String employeeId) {
		this.teamId = teamId;
		this.employeeId = employeeId;
	}

	public TeamMemberManagement() {
		
	}
	
	public boolean isEqual(String teamId, String emplId) {
		if(this.teamId.trim().equalsIgnoreCase(teamId.trim()) && this.employeeId.trim().equals(emplId.trim())) {
			return true;
		}
		return false;
	}
}
