package com.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TEAM")
public class Team {
	@Id
	@Column(name = "team_id")
	private String teamId;
	@Column(name = "team_name", nullable = false)
	private String teamName;
	@Column(name = "department_id", nullable = false)
	private String departmentId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "department_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Department department;
	
	public Team() {
		
	}
	public Team(String teamId, String teamName, String departmentId) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.departmentId = departmentId;
	}
	
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
