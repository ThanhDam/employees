package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.DepartmentEmployee;
import com.employee.model.Employee;
import com.employee.model.EmployeeInfo;
import com.employee.model.EmployeeRole;
import com.employee.model.Role;
import com.employee.model.TeamEmployee;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.RoleRepository;

@RestController
public class EmployeeApiController {
	private final static int MIN_LIMIT = 0;
	private final static int MAX_LIMIT = 1500;
	
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@GetMapping(value = "/employees")
	protected List<Employee> getAll(){
		Page<Employee> pageEmploy = employeeRepo.findAll(PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "/employees/roles/{id}")
	protected List<EmployeeRole> getByRole(@PathVariable String id){
		Page<EmployeeRole> pageEmploy = employeeRepo.findByRole(id, PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "/employeesinfo")
	protected List<EmployeeInfo> getAllInfoEmployees(){
		Page<EmployeeInfo> pageEmploy = employeeRepo.findAllInfo(PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "employees/teams/{id}")
	protected List<TeamEmployee> getEmployeeByTeam(@PathVariable String id){
		Page<TeamEmployee> pageEmploy = employeeRepo.findEmployeeByTeam(id, PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "/employees/departments/{id}")
	protected List<DepartmentEmployee> getEmployeeByDepart(@PathVariable String id){
		Page<DepartmentEmployee> pageEmploy = employeeRepo.findEmployeeByDepartment(id, PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "/employees/departments/{depart}/{role}")
	protected List<Employee> getEmployeesByDepartmentRole(@PathVariable(name = "depart")String departId, 
			@PathVariable(name = "role")String roleId){
		Page<Employee> pageEmploy = employeeRepo.findEmployeeByDepartmentRole(departId, roleId, PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "/employees/teams/{team}/{role}")
	protected List<Employee> getEmployeesByTeamRole(@PathVariable(name = "team")String teamId,
			@PathVariable(name = "role")String roleId){
		Page<Employee> pageEmploy = employeeRepo.findEmployeeByTeamRole(teamId, roleId, PageRequest.of(MIN_LIMIT, MAX_LIMIT));
		return pageEmploy.getContent();
	}
	
	@GetMapping(value = "/employees/{id}")
	protected ResponseEntity<Object> getById(@PathVariable String id) {
		Employee empl = employeeRepo.findById(id).orElse(new Employee());
		if(empl.getEmployeeId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(empl);
	}
	
	@PostMapping(value = "/employees")
	protected ResponseEntity<Object> addEmployee(@Valid @RequestBody Employee empl) {
		Employee emplTemp= employeeRepo.findById(empl.getEmployeeId()).orElse(new Employee());
		if(emplTemp.getEmployeeId() != null) {
			return new ResponseEntity<Object>("Employee existed", HttpStatus.CONFLICT);
		}
		Role role = roleRepo.findById(empl.getRoleId()).orElse(new Role());
		if(role.getRoleId() == null) {
			return new ResponseEntity<Object>("Role invalid", HttpStatus.NOT_FOUND);
		}
		employeeRepo.save(empl);
		return new ResponseEntity<Object>(empl, HttpStatus.CREATED);		
	}
	
	@PutMapping(value = "/employees")
	protected ResponseEntity<Object> updateEmployeeById(@Valid @RequestBody Employee newEmpl){
		Employee empl = employeeRepo.findById(newEmpl.getEmployeeId()).orElse(new Employee());
		if(empl.getEmployeeId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		empl.setEmployeeLastName(newEmpl.getEmployeeLastName());
		empl.setEmployeeFirstName(newEmpl.getEmployeeFirstName());
		empl.setDob(newEmpl.getDob());
		Role role = roleRepo.findById(newEmpl.getRoleId()).orElse(new Role());
		if(role.getRoleId() == null) {
			return new ResponseEntity<Object>("Role invalid", HttpStatus.NOT_FOUND);
		}
		empl.setRoleId(newEmpl.getRoleId());
		return ResponseEntity.ok(employeeRepo.save(empl));
	}
	
	@DeleteMapping(value = "/employees/{id}")
	protected ResponseEntity<String> deleteEmployeeById(@PathVariable String id){
		Employee empl = employeeRepo.findById(id).orElse(new Employee());
		if(empl.getEmployeeId() == null) {
			return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
		}
		employeeRepo.delete(empl);
		return ResponseEntity.ok("Deleted");
	}
}
