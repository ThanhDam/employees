package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.employee.model.DepartmentEmployee;
import com.employee.model.Employee;
import com.employee.model.EmployeeInfo;
import com.employee.model.EmployeeRole;
import com.employee.model.TeamEmployee;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Test
	protected void testGetAll(){
		List<Employee> lstEmploy = employeeRepo.findAll();
		assertThat(lstEmploy.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetByRole() {
		List<EmployeeRole> lstEmploy = employeeRepo.findByRole("01");
		assertThat(lstEmploy.size()).isGreaterThan(0);
		assertThat(lstEmploy.get(0).getId()).isEqualTo("5000000001");
	}
	
	@Test
	protected void testGetAllInfoEmployees() {
		List<EmployeeInfo> lstEmploy = employeeRepo.findAllInfo();
		assertThat(lstEmploy.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetEmployeeByTeam() {
		List<TeamEmployee> lstEmployee = employeeRepo.findEmployeeByTeam("BRI");
		assertThat(lstEmployee.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetEmployeeByDepart() {
		List<DepartmentEmployee> lstEmploy = employeeRepo.findEmployeeByDepartment("LIF");
		assertThat(lstEmploy.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetEmployeesByDepartmentRole() {
		List<Employee> lstEmployees = employeeRepo.findEmployeeByDepartmentRole("PNC", "02");
		assertThat(lstEmployees.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetEmployeesByTeamRole() {
		List<Employee> lstEmploy = employeeRepo.findEmployeeByTeamRole("SIT", "03");
		assertThat(lstEmploy.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetById(){
		Employee employ = employeeRepo.findById("5000000011").orElse(new Employee());
		assertThat(employ.getEmployeeId()).isEqualTo("5000000011");
	}
	
	@Test
	protected void testAddEmployee() {
		List<Employee> lstEmploy = employeeRepo.findAll();
		String suffix = Integer.toString(lstEmploy.size()+1).trim();
		String init = "5000000000";
		String prefix = init.substring(0, init.length()-suffix.length());
		String id = prefix.concat(suffix);
		Employee employ = employeeRepo.save(new Employee(id, "Nguyen", "K", "04", new Date(1990, 5, 4)));
		assertThat(employ.getEmployeeId().trim()).isEqualTo(id);
	}
	
	@Test
	protected void testUpdateEmployeeById() {
		Employee employ = employeeRepo.findById("5000000017").orElse(new Employee());
		employ.setEmployeeFirstName("An");
		employeeRepo.save(employ);
		Employee employUpdate = employeeRepo.findById("5000000017").orElse(new Employee());
		assertThat(employUpdate.getEmployeeFirstName().trim()).isEqualTo("An");
	}
	
	@Test
	protected void testDeleteEmployeeById() {
		Employee employ = employeeRepo.findById("5000000019").orElse(new Employee());
		employeeRepo.delete(employ);
		Employee employDelete = employeeRepo.findById("5000000019").orElse(new Employee());
		assertThat(employDelete.getEmployeeId()).isNull();
	}
}

