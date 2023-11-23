package com.espacio.bumeran.aula.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espacio.bumeran.aula.mapper.CoursesMapper;
//import com.espacio.bumeran.aula.mapper.CourseMapper;
import com.espacio.bumeran.aula.mapper.UserRepositoryImp;
import com.espacio.bumeran.aula.model.Course;
import com.espacio.bumeran.aula.model.RegisterUser;
import com.espacio.bumeran.aula.model.SignInCourses;

//import jakarta.servlet.http.HttpSession;




@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AulaBumeranController {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepositoryImp userRepository;
	
	
	@Autowired
	private CoursesMapper coursesMapper;

	
	@GetMapping("/test")
	public String test (){
		return "TEST OK";
	}
	

	@PostMapping(path="/users/register", consumes = "application/json")
	public void register (@RequestBody RegisterUser registerUser) {
		String passwordEncode = passwordEncoder.encode(registerUser.getPassword());
		userRepository.insertUser(registerUser.getEmailAddress(), registerUser.getFirstName(), registerUser.getLastName(), passwordEncode, true);		
		userRepository.insertRoleUser(registerUser.getEmailAddress(), "ALUMNO");
	}
	
	@GetMapping(path="/academy/getUserCourses", produces = "application/json")
	public List<Course> getUserCourses (@RequestParam String userName) {
		System.out.println("gETuSERcOURSES REQUEST " + userName);
		List<Course> userCourses = coursesMapper.getUserCourses(userName);
		return userCourses;
		//return null;
	}	
	
	@PostMapping(path="/teacher/postImage")
	public void postImage () throws Exception{
		
	    File file = new File("C:\\Users\\6835938\\eclipse-workspace\\rest-espaciobumeran\\src\\main\\resources\\muerde_la_manzana.jpeg");
	    byte[] bytes = new byte[(int) file.length()];

	    // funny, if can use Java 7, please uses Files.readAllBytes(path)
	    try(FileInputStream fis = new FileInputStream(file)){
	        fis.read(bytes);
	    }		
		
		
		//byte[] bytes = Files.readAllBytes(Paths.get(new URI("classpath:muerde_la_manzana.jpeg")));
		coursesMapper.updateCourseImage(3, bytes);
		System.out.println("Imagen actualizada.");
	}	

	
	@PostMapping(path="/users/signinOnlineCourses", consumes = "application/json")
	public void signinOnlineCourses (@RequestBody SignInCourses signInCourses) {
		/*String passwordEncode = passwordEncoder.encode(registerUser.getPassword());
		userRepository.insertUser(registerUser.getEmailAddress(), registerUser.getFirstName(), registerUser.getLastName(), passwordEncode, true);		
		userRepository.insertRoleUser(registerUser.getEmailAddress(), "ALUMNO");*/
		System.out.println(signInCourses.toString());
	}


}