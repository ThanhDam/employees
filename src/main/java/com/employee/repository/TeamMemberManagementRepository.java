package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.TeamMemberId;
import com.employee.model.TeamMemberManagement;

@Repository
public interface TeamMemberManagementRepository extends JpaRepository<TeamMemberManagement, TeamMemberId>{
	@Query("SELECT m FROM TeamMemberManagement m WHERE m.teamId LIKE %?1%")
	List<TeamMemberManagement> findByTeamId(String teamId);
}
