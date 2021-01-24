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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Department;
import com.employee.model.Team;
import com.employee.model.TeamMemberManagement;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.TeamMemberManagementRepository;
import com.employee.repository.TeamRepository;

@RestController
public class TeamApiController {
	@Autowired
	TeamRepository teamRepo;
	@Autowired
	DepartmentRepository departmentRepo;
	
	@GetMapping(value = "/teams")
	protected List<Team> getAll(){
		return teamRepo.findAll();
	}
	
	@GetMapping(value = "/teams/departments/{id}")
	protected List<Team> getTeamByDepart(@PathVariable String id){
		List<Team> lstTeam = teamRepo.findByDepartment(id);
		return lstTeam;
	}
	
	@GetMapping(value = "/teams/{id}")
	protected ResponseEntity<Object> getById(@PathVariable String id){
		Team team = teamRepo.findById(id).orElse(new Team());
		if(team.getTeamId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(team);
	}
	
	@PostMapping(value = "/teams")
	protected ResponseEntity<Object> addTeam(@Valid @RequestBody Team newTeam){
		Team teamTmp = teamRepo.findById(newTeam.getTeamId()).orElse(new Team());
		if(teamTmp.getTeamId() != null) {
			return new ResponseEntity<Object>("Team existed",HttpStatus.CONFLICT);
		}
		Department depart = departmentRepo.findById(newTeam.getDepartmentId()).orElse(new Department());
		if(depart.getDepartmentId() == null) {
			return new ResponseEntity<Object>("Department invalid", HttpStatus.NOT_FOUND);
		}
		teamRepo.save(newTeam);
		return new ResponseEntity<Object>("Created", HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/teams")
	protected ResponseEntity<Object> updateTeam(@Valid @RequestBody Team team){
		Team teamTmp = teamRepo.findById(team.getTeamId()).orElse(new Team());
		if(teamTmp.getTeamId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		teamTmp.setTeamName(team.getTeamName());
		Department depart = departmentRepo.findById(team.getDepartmentId()).orElse(new Department());
		if(depart.getDepartmentId() == null) {
			return new ResponseEntity<Object>("Department invalid", HttpStatus.NOT_FOUND);
		}
		teamTmp.setDepartmentId(team.getDepartmentId());
		return ResponseEntity.ok(teamRepo.save(teamTmp));
	}
	
	@DeleteMapping(value = "/teams/{id}")
	protected ResponseEntity<String> deleteTeam(@PathVariable String id){
		Team team = teamRepo.findById(id).orElse(new Team());
		if(team.getTeamId() == null) {
			return new ResponseEntity<String>("Not found", HttpStatus.NOT_FOUND);
		}
		teamRepo.delete(team);
		return ResponseEntity.ok("Deleted");
	}
}
