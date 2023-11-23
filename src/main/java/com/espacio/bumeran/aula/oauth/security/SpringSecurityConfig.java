package com.espacio.bumeran.aula.oauth.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
//@EnableAuthorizationServer
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private DataSource dataSource;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      .authorizeRequests()
	        .anyRequest().fullyAuthenticated()
	        .and()
	      .httpBasic();
	  }
	
	

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from users where username = ?")
        			.authoritiesByUsernameQuery("select username as principal, authority as role from authorities where username=?")
        				.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");

	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {		
		return super.authenticationManager();
	}
	
	
	

}
