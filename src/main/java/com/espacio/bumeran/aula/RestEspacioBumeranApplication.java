package com.espacio.bumeran.aula;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.WebApplicationInitializer;


//@EnableConfigServer
@SpringBootApplication
//@PropertySource("file:/var/properties/moa-infra.properties")
@ComponentScan(basePackages = { "com.espacio.bumeran.aula" })
@Configuration
public class RestEspacioBumeranApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	//private static final Logger log = LoggerFactory.getLogger(RestEspacioBumeranApplication.class);
	
	public static void main(String[] args) {
		//log.debug("INICIO REST ESPACIO BUMERAN AULA");

		
		SpringApplication.run(RestEspacioBumeranApplication.class, args);
	}

}
