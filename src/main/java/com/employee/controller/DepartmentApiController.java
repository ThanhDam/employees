package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Department;
import com.employee.model.Team;
import com.employee.model.TeamMemberManagement;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.TeamMemberManagementRepository;
import com.employee.repository.TeamRepository;

@RestController
public class DepartmentApiController {
	@Autowired
	DepartmentRepository departmentRepo;
	@Autowired
	TeamRepository teamRepo;
	@Autowired
	TeamMemberManagementRepository tmmRepo;
	
	@GetMapping(value = "/departments")
	protected List<Department> getAll(){
		return departmentRepo.findAll();
	}
	
	@GetMapping(value = "/departments/{id}")
	protected ResponseEntity<Object> getById(@PathVariable String id){
		Department depart = departmentRepo.findById(id).orElse(new Department());
		if(depart.getDepartmentId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(depart);
	}
	
	@PostMapping(value = "/departments")
	protected ResponseEntity<Object> addDepartment(@Valid @RequestBody Department newDepart){
		Department depart = departmentRepo.findById(newDepart.getDepartmentId()).orElse(new Department());
		if(depart.getDepartmentId() != null) {
			return new ResponseEntity<Object>("Department existed", HttpStatus.CONFLICT);
		}
		departmentRepo.save(newDepart);
		return new ResponseEntity<Object>(newDepart, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/departments")
	protected ResponseEntity<Object> updateDepartment(@Valid @RequestBody Department depart){
		Department departTemp = departmentRepo.findById(depart.getDepartmentId()).orElse(new Department());
		if(departTemp.getDepartmentId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		departTemp.setDeparmentName(depart.getDeparmentName());
		return ResponseEntity.ok(departmentRepo.save(departTemp));
	}
	
	@DeleteMapping(value = "/departments/{id}")
	protected ResponseEntity<String> deleteDepartment(@PathVariable String id){
		List<Team> lstTeam = teamRepo.findByDepartment(id);
		if(lstTeam.size() == 0) {
			return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
		}
		for (Team team : lstTeam) {
			List<TeamMemberManagement> lstTmm = tmmRepo.findByTeamId(team.getTeamId());
			if(lstTmm.size() > 0) {
				for(TeamMemberManagement member: lstTmm) {
					tmmRepo.delete(member);
				}
			}
			teamRepo.delete(team);
		}
		return ResponseEntity.ok("Deleted");
	}

}
