package com.espacio.bumeran.aula.model;

import java.util.ArrayList;
import java.util.List;

public class Inscription {
	private String inscriptionId;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private String pack;
	private String courseDesc;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	private List<String> workshops;

	public String getInscriptionId() {
		return inscriptionId;
	}
	public void setInscriptionId(String inscriptionId) {
		this.inscriptionId = inscriptionId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public List<String> getCourses() {
		return workshops;
	}
	
	public void setWorkshops(List<String> courses) {
		this.workshops = courses;
	}
	
	public void addWorkshops (String course) {
		if (this.workshops == null)
			this.workshops = new ArrayList<String>();
		
		this.workshops.add(course);
	}
}
