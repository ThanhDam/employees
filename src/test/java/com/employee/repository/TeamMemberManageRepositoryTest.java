package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.employee.model.TeamMemberId;
import com.employee.model.TeamMemberManagement;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TeamMemberManageRepositoryTest {
	@Autowired
	TeamMemberManagementRepository tmmRepo;
	
	@Test
	protected void testGetAll() {
		List<TeamMemberManagement> lstMemberTeam = tmmRepo.findAll();
		assertThat(lstMemberTeam.size()).isGreaterThan(0);
	}
	@Test
	protected void testAddMemberInTeam() {
		TeamMemberManagement newTmm = new TeamMemberManagement("PUM","5000000015");
		TeamMemberManagement tmm = tmmRepo.save(newTmm);
		assertThat(tmm.isEqual(newTmm.getTeamId().trim(), newTmm.getEmployeeId().trim()));
	}
	@Test
	protected void testDeleteMemberInTeam() {
		TeamMemberManagement tmm = tmmRepo.findById(new TeamMemberId("PUM", "5000000003")).orElse(new TeamMemberManagement());
		tmmRepo.delete(tmm);
		TeamMemberManagement tmmDelete = tmmRepo.findById(new TeamMemberId("PUM", "5000000003")).orElse(new TeamMemberManagement());
		assertThat(tmmDelete.getTeamId()).isNull();
	}
}
