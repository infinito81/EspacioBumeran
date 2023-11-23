package com.espacio.bumeran.aula.mapper;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.espacio.bumeran.aula.model.Course;

public interface CoursesMapper {
	List<Course> getUserCourses(@Param("userName") String userName);

	void updateCourseImage(@Param("idCurso") int idCurso, @Param("imageBytes") byte[] imageBytes); 
	
	int insertCourseInscription(@Param("courseId") int courseId, @Param("inscriptionId") int inscriptionId);
	
	int insertInscription(@Param("email") String email, @Param("nombre") String nombre, @Param("apellidos") String apellidos, 
			@Param("telefono") String telefono, @Param("pack") String pack);

	int getInscriptionId(@Param("email") String email);
}
