package com.espacio.bumeran.aula.oauth.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class InfoAdicionalToken implements TokenEnhancer{

	private Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);
	

	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		//Map<String, Object> aditionalInfo = new HashMap<String, Object>();
		log.debug("LOGGING DEL USUARIO " + authentication.getUserAuthentication().getName());		
	
		return accessToken;
		
	}
	
	

}
