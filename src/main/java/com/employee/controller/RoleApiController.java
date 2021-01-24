package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Role;
import com.employee.repository.RoleRepository;

@RestController
public class RoleApiController {
	@Autowired
	RoleRepository roleRepo;
	
	@GetMapping(value = "/roles")
	protected List<Role> getAll(){
		return roleRepo.findAll();
	}
	
	@GetMapping(value = "/roles/{id}")
	protected ResponseEntity<Object> getById(@PathVariable String id){
		Role role = roleRepo.findById(id).orElse(new Role());
		if(role.getRoleId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(role);
	}
	
	@PostMapping(value = "/roles")
	protected ResponseEntity<Object> addRole(@Valid @RequestBody Role newRole){
		Role role = roleRepo.findById(newRole.getRoleId()).orElse(new Role());
		if(role.getRoleId() != null) {
			return new ResponseEntity<Object>("Role existed", HttpStatus.CONFLICT);
		}
		roleRepo.save(newRole);
		return new ResponseEntity<Object>(newRole, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/roles")
	protected ResponseEntity<Object> updateRole(@Valid @RequestBody Role role){
		Role roleTmp = roleRepo.findById(role.getRoleId()).orElse(new Role());
		if(roleTmp.getRoleId() == null) {
			return new ResponseEntity<Object>("Not found", HttpStatus.NOT_FOUND);
		}
		roleTmp.setRoleName(role.getRoleName());
		return ResponseEntity.ok(roleRepo.save(roleTmp));
	}
}
