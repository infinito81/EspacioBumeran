package com.espacio.bumeran.aula.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;
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
import com.espacio.bumeran.aula.mapper.VisitMapper;
import com.espacio.bumeran.aula.model.Course;
import com.espacio.bumeran.aula.model.Inscription;
import com.espacio.bumeran.aula.model.RegisterUser;
import com.espacio.bumeran.aula.model.SignInCourses;
import com.espacio.bumeran.aula.services.SendMailService;

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
	private VisitMapper visitMapper;	
	
	@Autowired
	private CoursesMapper coursesMapper;
	
	@Autowired 
	private SendMailService sendMailService;

	
	@GetMapping(path="/test", produces = "text/plain")
	public String test (){		
		return "TEST OK";
	}
	
	
	@GetMapping(path="/test/visitRegistration")
	public void visitRegistration (@RequestParam String page){
		System.out.println("Han entrado en la web --> " + page);
		
		visitMapper.insertVisit(page);
		
		System.out.println("Fin registro visita");
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
	
	@GetMapping(path="/users/getInscriptions", produces = "application/json")
	public List<Inscription> getInscriptions () {
		System.out.println("getInscriptions " );
		List<Inscription> inscriptions = coursesMapper.getAllInscriptions();
		return inscriptions;
	}
	
	
	@GetMapping(path="/users/getInscriptionsEvent", produces = "application/json")
	public List<Inscription> getInscriptionsEvent (@RequestParam String event) {
		System.out.println("getInscriptions. Particular event : " + event );
		List<Inscription> inscriptions = coursesMapper.getInscriptionsEvent(event);
		return inscriptions;
	}		
		

	
	@PostMapping(path="/users/signinOnlineCourses", consumes = "application/json")
	public String signinOnlineCourses (@RequestBody SignInCourses signInCourses) {
		System.out.println(signInCourses.toString());

		
		//System.out.println("Precio " + precio);
		
		int resultInscription = coursesMapper.insertInscription(signInCourses.getEmailAddress(), signInCourses.getFirstName(), signInCourses.getLastName(), signInCourses.getPhone(), signInCourses.getPack());
		
		System.out.println("Inscripcion " + resultInscription);
		
		int inscriptionId = coursesMapper.getInscriptionId(signInCourses.getEmailAddress());
		System.out.println("Inscripcion id " + inscriptionId);
		
		/*if (signInCourses.isCuentos()) {
			coursesMapper.insertCourseInscription(1, inscriptionId);
		}
		
		if (signInCourses.isVinculos()) {
			coursesMapper.insertCourseInscription(2, inscriptionId);
		} 
		
		if (signInCourses.isInfluencers()) {
			coursesMapper.insertCourseInscription(3, inscriptionId);
		} 
		
		if (signInCourses.isZapatos()) {
			coursesMapper.insertCourseInscription(4, inscriptionId);
		} 
		
		if (signInCourses.isLimites()) {
			coursesMapper.insertCourseInscription(5, inscriptionId);
		} */
		
		String subject = "PreInscripción a talleres online Espacio Bumeran. Id de Inscripción: " + inscriptionId;
		
		String precio = "25";
		if (signInCourses.getPack().equals("2")) {
			precio = "30";	
		} else if (signInCourses.getPack().equals("3")) {
			precio = "50";
		} else if (signInCourses.getPack().equals("11")) {
			precio = "249";
			subject = "Inscripción a Retiro Toledo de Espacio Bumerán. Id de Inscripción: " + inscriptionId;
		} else if (signInCourses.getPack().equals("12")) {
			precio = "450";			
			subject = "Inscripción a Retiro Toledo de Espacio Bumerán. Id de Inscripción: " + inscriptionId;
		} else if (signInCourses.getPack().equals("21")) {
			if (!signInCourses.isSocioeugenio() && !signInCourses.isSociotorre()) {
				precio = "18";
				coursesMapper.insertCourseInscription(21, inscriptionId);
			} else {
				precio = "15";
				coursesMapper.insertCourseInscription(21, inscriptionId);
			}
			subject = "Inscripción a taller Entrenador@s Emocionales. Id de Inscripción: " + inscriptionId;
		}
		
		
		String body = "¡ENHORABUENA!\r\n"
				+ "    Tu Inscripción al taller de Espacio Bumerán ha sido realizada con éxito.\r\n"
				+ "    Para confirmar la inscripción a los talleres tendrás que abonar la cantidad de " + precio + "€ \r\n" 
				+ "    Puedes abonarlo en un plazo de dos días desde que recibas este mail:\r\n"
				+ "    - Vía Bizum al número de teléfono: 618210095\r\n"
				+ "    - Vía Transferencia al número de cuenta ES51 3190 2099 17 5819838227\r\n\n"
				+ "    (*) Recuerda poner en el concepto el identificador de inscripción " + inscriptionId +" o tu email";
		
		if (signInCourses.getPack().equals("11") || signInCourses.getPack().equals("12")) {
			body = "¡ENHORABUENA!\r\n"
					+ "    Tu Inscripción al Retiro Toledo de Espacio Bumerán ha sido realizada con éxito.\r\n"
					+ "    Para confirmar la inscripción al evento tendrás que abonar la cantidad de " + precio + "€ en un plazo de 24 horas desde que recibas este mail\r\n" 
					+ "    Puedes abonarlo:\r\n"
					+ "    - Vía Bizum al número de teléfono: 618210095\r\n"
					+ "    - Vía Transferencia al número de cuenta ES51 3190 2099 17 5819838227\r\n\n"
					+ "    (*) Recuerda poner en el concepto el identificador de inscripción " + inscriptionId +" o tu email\r\n\n"
					+ "	   (*) En caso de que no salga el mínimo grupo para el retiro, el dinero será devuelto íntegramente.";	

		} else if (signInCourses.getPack().equals("21")){
			body = "¡ENHORABUENA!\r\n"
					+ "    Tu Inscripción al taller ENTRENADOR@S EMOCIONALES de Espacio Bumerán ha sido realizada con éxito.\r\n"
					+ "    Para confirmar la inscripción al evento tendrás que abonar la cantidad de " + precio + "€ en un plazo de 24 horas desde que recibas este mail\r\n" 
					+ "    Puedes abonarlo :\r\n"
					+ "    - Vía Bizum al número de teléfono: 618210095\r\n"
					+ "    - Vía Transferencia al número de cuenta ES51 3190 2099 17 5819838227\r\n\n"
					+ "    (*) Recuerda poner en el concepto el identificador de inscripción " + inscriptionId +" o tu email\r\n\n"
					+ "	   (*) En caso de que el curso no se realizara, el dinero será devuelto íntegramente.";			
		}
		
		System.out.println("Vamos a mandar mail a dirección cliente " + signInCourses.getEmailAddress());
		sendMailService.sendMail("inscripciones@espaciobumeran.com", signInCourses.getEmailAddress(), subject, body);
		
		System.out.println("Vamos a mandar copia del mail a espaciobumeran@gmail.com");
		sendMailService.sendMail("inscripciones@espaciobumeran.com", "espaciobumeran@gmail.com", subject, body);
		

		
		return inscriptionId + "";
	}


}