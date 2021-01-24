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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.TeamMemberId;
import com.employee.model.TeamMemberManagement;
import com.employee.repository.TeamMemberManagementRepository;

@RestController
public class TeamMemberManagementApiController {
	@Autowired
	TeamMemberManagementRepository teamManaRepo;
	
	@GetMapping(value = "/membermanage")
	protected List<TeamMemberManagement> getAll(){
		return teamManaRepo.findAll();
	}
	
	@PostMapping(value = "/membermanage")
	protected ResponseEntity<Object> addMemberInTeam(@Valid @RequestBody TeamMemberId member){
		TeamMemberManagement tmm = teamManaRepo.findById(member).orElse(new TeamMemberManagement());
		if(tmm.getTeamId() != null) {
			return new ResponseEntity<Object>("Member existed in Team", HttpStatus.CONFLICT);
		}
		teamManaRepo.save(new TeamMemberManagement(tmm.getTeamId(), tmm.getEmployeeId()));
		return new ResponseEntity<Object>("Created", HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/membermanage/{team}/{employee}")
	protected ResponseEntity<Object> deleteMemberInTeam(@PathVariable(name = "team")String teamId,
			@PathVariable(name = "employee") String employeeId){
		TeamMemberId id = new TeamMemberId(teamId, employeeId);
		TeamMemberManagement tmm = teamManaRepo.findById(id).orElse(new TeamMemberManagement());
		if(tmm.getTeamId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		teamManaRepo.delete(new TeamMemberManagement(teamId, employeeId));
		return new ResponseEntity<Object>("Deleted", HttpStatus.OK);
	}
}
