package com.espacio.bumeran.aula.mapper;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.espacio.bumeran.aula.model.Course;

public interface CoursesMapper {
	List<Course> getUserCourses(@Param("userName") String userName);

	void updateCourseImage(@Param("idCurso") int idCurso, @Param("imageBytes") byte[] imageBytes); 
}
