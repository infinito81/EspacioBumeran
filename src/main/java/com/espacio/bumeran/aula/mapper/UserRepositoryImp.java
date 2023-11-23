package com.espacio.bumeran.aula.mapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImp implements UserMapper {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String getUserRole(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(String userName, String firstName, String lastName, String password, boolean enabled) {
		String queryInsertUser = "INSERT INTO users VALUES ('" + userName + "','" + password + "'," + enabled + ",'" + firstName + "','" + lastName + "');";
		System.out.println(queryInsertUser);
		jdbcTemplate.update(queryInsertUser);
		System.out.println("usuario insertado se supone");
	}

	public void insertRoleUser(String userName, String role) {
		String queryInsertRoleUser = "INSERT INTO authorities VALUES ('" + userName + "','" + role + "');";
		System.out.println(queryInsertRoleUser);
		jdbcTemplate.update(queryInsertRoleUser);
		System.out.println("insertado rol");
	}

}
