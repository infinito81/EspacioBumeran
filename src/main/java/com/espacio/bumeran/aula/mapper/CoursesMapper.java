package com.espacio.bumeran.aula.mapper;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.espacio.bumeran.aula.model.Course;
import com.espacio.bumeran.aula.model.Inscription;

public interface CoursesMapper {
	List<Course> getUserCourses(@Param("userName") String userName);

	void updateCourseImage(@Param("idCurso") int idCurso, @Param("imageBytes") byte[] imageBytes); 
	
	int insertCourseInscription(@Param("courseId") int courseId, @Param("inscriptionId") int inscriptionId);
	
	int insertInscription(@Param("email") String email, @Param("nombre") String nombre, @Param("apellidos") String apellidos, 
			@Param("telefono") String telefono, @Param("pack") String pack, @Param("city") String city);

	int getInscriptionId(@Param("email") String email);

	List<Inscription> getAllInscriptions();
	
	List<Inscription> getInscriptionsEvent(@Param("event") String event);
}
