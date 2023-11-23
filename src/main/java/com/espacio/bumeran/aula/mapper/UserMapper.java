package com.espacio.bumeran.aula.mapper;


import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
	

	public String getUserRole(String userCode);
	
	public void insertUser(String userName, String fistName, String lastName, String password, boolean enabled);
}
