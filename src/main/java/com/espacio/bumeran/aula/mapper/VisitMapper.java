package com.espacio.bumeran.aula.mapper;

import org.springframework.data.repository.query.Param;

public interface VisitMapper {

	int insertVisit(@Param("page") String page);

}
