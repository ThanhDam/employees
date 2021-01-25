package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
		Page<Employee> pageEmploy = employeeRepo.findAll(PageRequest.of(0, 10));
		assertThat(pageEmploy.getContent().size()).isEqualTo(10);
	}
	
	@Test
	protected void testGetByRole() {
		Page<EmployeeRole> pageEmploy = employeeRepo.findByRole("04", PageRequest.of(0, 10));
		assertThat(pageEmploy.getContent().size()).isEqualTo(10);
	}
	
	@Test
	protected void testGetAllInfoEmployees() {
		Page<EmployeeInfo> pageEmploy = employeeRepo.findAllInfo(PageRequest.of(0, 10));
		assertThat(pageEmploy.getContent().size()).isEqualTo(10);
	}
	
	@Test
	protected void testGetEmployeeByTeam() {
		Page<TeamEmployee> pageEmployee = employeeRepo.findEmployeeByTeam("BRI", PageRequest.of(0, 3));
		assertThat(pageEmployee.getContent().size()).isEqualTo(3);
	}
	
	@Test
	protected void testGetEmployeeByDepart() {
		Page<DepartmentEmployee> pageEmploy = employeeRepo.findEmployeeByDepartment("LIFINSUR", PageRequest.of(0, 3));
		assertThat(pageEmploy.getContent().size()).isEqualTo(3);
	}
	
	@Test
	protected void testGetEmployeesByDepartmentRole() {
		Page<Employee> pageEmployees = employeeRepo.findEmployeeByDepartmentRole("PNC", "02", PageRequest.of(0, 1));
		assertThat(pageEmployees.getContent().size()).isEqualTo(1);
	}
	
	@Test
	protected void testGetEmployeesByTeamRole() {
		Page<Employee> pageEmploy = employeeRepo.findEmployeeByTeamRole("SIT", "04", PageRequest.of(0, 4));
		assertThat(pageEmploy.getContent().size()).isEqualTo(4);
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

