package com.employee;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/employees", "/teams", "/departments", "/role")
		.hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.POST, "/employees", "/teams", "/departments", "/role")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/employees", "/teams", "/departments", "/role")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/employees", "/teams")
		.hasRole("ADMIN")
		.anyRequest().authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder pswEncode = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication()
		.withUser("user")
		.password(pswEncode.encode("password"))
		.roles("USER")
		.and()
		.withUser("admin")
		.password(pswEncode.encode("p@ssword"))
		.roles("USER", "ADMIN");
	}
}
