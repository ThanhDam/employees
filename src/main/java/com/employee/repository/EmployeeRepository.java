package com.employee.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.model.DepartmentEmployee;
import com.employee.model.Employee;
import com.employee.model.EmployeeInfo;
import com.employee.model.EmployeeRole;
import com.employee.model.TeamEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	@Query("SELECT new com.employee.model.EmployeeRole(e.employeeId, e.employeeLastName, e.employeeFirstName, e.dob, r.roleName)"
			+ " FROM Employee e JOIN e.role r WHERE e.roleId = ?1")
	List<EmployeeRole> findByRole(String roleId);
	
	@Query("SELECT new com.employee.model.EmployeeInfo(e.employeeId, e.employeeLastName, e.employeeFirstName, e.dob, "
			+ "r.roleName, t.teamName, d.departmentName) "
			+ "FROM TeamMemberManagement m JOIN m.employee e JOIN m.team t JOIN t.department d JOIN e.role r")
	List<EmployeeInfo> findAllInfo();
	
	@Query("SELECT new com.employee.model.TeamEmployee(e.employeeId, e.employeeLastName, e.employeeFirstName, e.dob, t.teamName) "
			+ "FROM TeamMemberManagement m JOIN m.employee e JOIN m.team t WHERE t.teamId LIKE %?1%")
	List<TeamEmployee> findEmployeeByTeam(String idTeam);
	
	@Query("SELECT new com.employee.model.DepartmentEmployee(e.employeeId, e.employeeLastName, e.employeeFirstName, e.dob, d.departmentName) "
			+ "FROM TeamMemberManagement m JOIN m.employee e JOIN m.team t JOIN t.department d "
			+ "WHERE d.departmentId LIKE %?1%")
	List<DepartmentEmployee> findEmployeeByDepartment(String idDep);
	
	@Query("SELECT DISTINCT e FROM TeamMemberManagement m JOIN m.employee e JOIN m.team t JOIN t.department d "
			+ "WHERE d.departmentId LIKE %?1% AND e.roleId = ?2")
	List<Employee> findEmployeeByDepartmentRole(String departId, String roleId);
	
	@Query("SELECT DISTINCT  e FROM TeamMemberManagement m JOIN m.employee e JOIN m.team t "
			+ "WHERE t.teamId LIKE %?1% AND e.roleId = ?2")
	List<Employee> findEmployeeByTeamRole(String teamId, String roleId);
}
