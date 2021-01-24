package com.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.employee.model.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoleRepositoryTest {
	@Autowired
	RoleRepository roleRepo;

	@Test
	protected void testGetAll() {
		List<Role> lstRole = roleRepo.findAll();
		assertThat(lstRole.size()).isGreaterThan(0);
	}

	@Test
	protected void testGetById() {
		Role role = roleRepo.findById("01").orElse(new Role());
		assertThat(role.getRoleId().trim()).isEqualTo("01");
	}

	@Test
	protected void testAddRole() {
		Role role = roleRepo.save(new Role("05","Internship"));
		assertThat(role.getRoleId().trim()).isEqualTo("05");
	}

	@Test
	protected void testUpdateRole() {
		Role role = roleRepo.findById("04").orElse(new Role());
		role.setRoleName("Members");
		roleRepo.save(role);
		Role roleUpdate = roleRepo.findById("04").orElse(new Role());
		assertThat(roleUpdate.getRoleName().trim()).isEqualTo("Members");
	}
}
