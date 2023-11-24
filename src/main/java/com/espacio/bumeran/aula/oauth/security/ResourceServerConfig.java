package com.espacio.bumeran.aula.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@RefreshScope
//@Configuration
//@RestController
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	//TODO configurar en algún fichero
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(resourceTokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//TODO configurar las rutas
		http.authorizeRequests()
		.antMatchers("/oauth/**").permitAll()
		.antMatchers("/api/teacher/**").permitAll()
		.antMatchers("/api/users/**").permitAll()
		.antMatchers("/api/test/**").permitAll()
		.antMatchers("/api/visitRegistration/**").permitAll()		
		.antMatchers("/api/academy/**").hasAnyRole("ALUMNO","ADMIN")
		//.antMatchers("/academy/**").hasAnyRole("ALUMNO","ADMIN")	
		.anyRequest().authenticated()		
		.and().cors().configurationSource(corsConfigurationSource());


	}
	
	
	@Bean
	 public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*","http://localhost:4200")); // <-- you may change "*"
	        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "TRACE"));
	        
	        //configuration.setAllowedMethods(Arrays.asList("GET"));
	        configuration.setAllowCredentials(true);
	        configuration.setAllowedHeaders(Arrays.asList(
	            "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
	            "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name","Access-Control-Allow-Request-Method"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);

	        return source;
	    }
	
	

	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	
	@Bean
	public TokenStore resourceTokenStore() {
		return new JwtTokenStore(resourceAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter resourceAccessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}
	

}
