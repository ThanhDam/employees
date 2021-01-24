package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
	@Query("SELECT t FROM Team t JOIN t.department d WHERE t.departmentId LIKE %?1%")
	List<Team> findByDepartment(String departId);
}
