package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.employee.model.Team;
import com.employee.model.TeamMemberManagement;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TeamRepositoryTest {
	@Autowired
	TeamRepository teamRepo;
	@Autowired
	TeamMemberManagementRepository tmmRepo;
	
	@Test
	protected void testGetAll() {
		List<Team> lstTeam = teamRepo.findAll();
		assertThat(lstTeam.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetTeamByDepart() {
		List<Team> lstTeam = teamRepo.findByDepartment("Bank");
		assertThat(lstTeam.size()).isGreaterThan(0);
	}
	
	@Test
	protected void testGetById() {
		Team team = teamRepo.findById("PUM").orElse(new Team());
		assertThat(team.getTeamId().trim()).isEqualTo("PUM");
	}
	
	@Test
	protected void testAddTeam() {
		Team team = teamRepo.save(new Team("IGI", "Insurance Pakistan", "LIFINSUR"));
		assertThat(team.getTeamId().trim()).isEqualTo("IGI");
	}
	
	@Test
	protected void testUpdateTeam() {
		Team team = teamRepo.findById("SIT").orElse(new Team());
		team.setDepartmentId("PNCINSUR");
		teamRepo.save(team);
		Team teamUpdate = teamRepo.findById("SIT").orElse(new Team());
		assertThat(teamUpdate.getDepartmentId().trim()).isEqualTo("PNCINSUR");
	}

	@Test
	protected void testDeleteTeam() {
		Team team = teamRepo.findById("IGI").orElse(new Team());
		teamRepo.delete(team);
		Team teamDelete = teamRepo.findById("IGI").orElse(new Team());
		assertThat(teamDelete.getTeamId()).isNull();
	}
	
}
