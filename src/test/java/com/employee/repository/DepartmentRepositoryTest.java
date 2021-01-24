package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.employee.model.Department;
import com.employee.model.Team;
import com.employee.model.TeamMemberManagement;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DepartmentRepositoryTest {
	@Autowired
	DepartmentRepository departmentRepo;
	@Autowired
	TeamRepository teamRepo;
	@Autowired
	TeamMemberManagementRepository tmmRepo;

	@Test
	protected void testGetAll() {
		List<Department> lstDepart = departmentRepo.findAll();
		assertThat(lstDepart.size()).isGreaterThan(0);
	}

	@Test
	protected void testGetById() {
		Department depart = departmentRepo.findById("BANK").orElse(new Department());
		assertThat(depart.getDepartmentId().trim()).isEqualTo("BANK");
	}

	@Test
	protected void testAddDepartment() {
		Department depart = departmentRepo.save(new Department("LOGISTIC", "Logitics"));
		assertThat(depart.getDepartmentId().trim()).isEqualTo("LOGISTIC");
	}

	@Test
	protected void testUpdateDepartment() {
		Department depart = departmentRepo.findById("BANK").orElse(new Department());
		depart.setDeparmentName("Banks");
		departmentRepo.save(depart);
		Department departUpdate = departmentRepo.findById("BANK").orElse(new Department());
		assertThat(departUpdate.getDeparmentName().trim()).isEqualTo("Banks");
	}
	
	@Test
	protected void testDeleteDepartment() {
		List<Team> lstTeam = teamRepo.findByDepartment("BANK");
		for(Team team: lstTeam) {
			List<TeamMemberManagement> lstMember = tmmRepo.findByTeamId(team.getTeamId());
			if(lstMember.size() > 0) {
				for(TeamMemberManagement member: lstMember) {
					tmmRepo.delete(member);
				}
			}
			teamRepo.delete(team);
		}
		List<Team> lstTeamDelete = teamRepo.findByDepartment("BANK");
		assertThat(lstTeamDelete.size()).isEqualTo(0);
	}
}
