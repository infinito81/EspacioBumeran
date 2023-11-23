package com.espacio.bumeran.aula.services;

import com.espacio.bumeran.aula.model.UserRole;

public interface IUserService {
	
	public UserRole findByUsuarioService(String userCode);
	
	public UserRole update(UserRole usuario, Long id);

}
