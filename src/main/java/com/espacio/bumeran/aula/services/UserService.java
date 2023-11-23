package com.espacio.bumeran.aula.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.espacio.bumeran.aula.mapper.UserMapper;
import com.espacio.bumeran.aula.model.UserRole;




@Service
public class UserService implements UserDetailsService, IUserService {

	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	//@Autowired
	//private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			
			log.debug("LOGGING username " + username);
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
			//String sUserRole = userMapper.getUserRole(username.toUpperCase());
			String sUserRole = "USER";
			UserRole userRole = new UserRole();
			userRole.setUserCode(username.toUpperCase());
			
			
			if (userRole!=null) {
				userRole.setUserRole(sUserRole.trim());	
			}
			
			List<UserRole> roles = new ArrayList<UserRole>();
			roles.add(userRole);
			
			userRole.setRoles(roles);
			
			authorities = userRole.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getUserRole())).collect(Collectors.toList());

			return new User(username, "VACIO", true, true, true, true, authorities);
		} catch (Exception ex) {
			log.error("Usuario no encontrado en MOA");
			throw new UsernameNotFoundException("Usuario no encontrado en MOA. " + ex.getMessage());
		}

	}


	@Override
	public UserRole findByUsuarioService(String userCode) {
		//String sUserRole = userMapper.getUserRole(userCode);
		String sUserRole = "ADMIN";
		
		UserRole userRole = new UserRole();
		userRole.setUserCode(userCode.trim());
		
		if (userRole!=null) {
			userRole.setUserRole(sUserRole.trim());	
		}
		
		
		return userRole;
	}


	


	@Override
	public UserRole update(UserRole usuario,
			Long id) {
		// TODO Auto-generated method stub
		return null;
	}



}
