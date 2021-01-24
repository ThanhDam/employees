package com.employee.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@Column(name = "employee_id")
	private String employeeId;
	@Column( name = "employee_last_name", nullable = false)
	private String employeeLastName;
	@Column( name = "employee_first_name", nullable = false)
	private String employeeFirstName;
	@Column( name = "role_id", nullable = false)
	private String roleId;
	@Column( name = "date_of_birth", nullable = false)
	private Date dob;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Role role;
	
	public Employee() {
		
	}
	public Employee(String employeeId, String employeeLastName, String employeeFirstName, String roleId, Date dob) {
		this.employeeId = employeeId;
		this.employeeLastName = employeeLastName;
		this.employeeFirstName = employeeFirstName;
		this.roleId = roleId;
		this.dob = dob;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
}
