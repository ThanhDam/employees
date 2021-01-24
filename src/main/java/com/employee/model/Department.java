package com.employee.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "DEPARTMENT")
public class Department {
	@Id
	@Column(name = "department_id")
	private String departmentId;
	@Column(name = "department_name", nullable = false)
	private String departmentName;
	
	public Department(String departmentId, String deparmentName) {
		this.departmentId = departmentId;
		this.departmentName = deparmentName;
	}
	
	public Department() {
		
	}

	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDeparmentName() {
		return departmentName;
	}
	public void setDeparmentName(String deparmentName) {
		this.departmentName = deparmentName;
	}
	
	
}
