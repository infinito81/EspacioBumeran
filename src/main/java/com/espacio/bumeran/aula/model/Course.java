package com.espacio.bumeran.aula.model;

import java.util.Arrays;
import java.util.Date;

public class Course {
	private String idCurso;
	private byte[] iconoCurso;
	private String txtoCorto;
	private String txtoMedio;
	private String txtoLargo;
	private String descripcionCorta;
	private String descripcionLarga;
	private String numHoras;
	private Date fechaInicio;
	
	
	@Override
	public String toString() {
		return "Course [idCurso=" + idCurso + ", iconoCurso=" + Arrays.toString(iconoCurso) + ", txtoCorto=" + txtoCorto
				+ ", txtoMedio=" + txtoMedio + ", txtoLargo=" + txtoLargo + ", descripcionCorta=" + descripcionCorta
				+ ", descripcionLarga=" + descripcionLarga + ", numHoras=" + numHoras + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", precio=" + precio + ", precioOferta=" + precioOferta + ", modalidad="
				+ modalidad + "]";
	}
	public String getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	public byte[] getIconoCurso() {
		return iconoCurso;
	}
	public void setIconoCurso(byte[] iconoCurso) {
		this.iconoCurso = iconoCurso;
	}
	public String getTxtoCorto() {
		return txtoCorto;
	}
	public void setTxtoCorto(String txtoCorto) {
		this.txtoCorto = txtoCorto;
	}
	public String getTxtoMedio() {
		return txtoMedio;
	}
	public void setTxtoMedio(String txtoMedio) {
		this.txtoMedio = txtoMedio;
	}
	public String getTxtoLargo() {
		return txtoLargo;
	}
	public void setTxtoLargo(String txtoLargo) {
		this.txtoLargo = txtoLargo;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	public String getDescripcionLarga() {
		return descripcionLarga;
	}
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}
	public String getNumHoras() {
		return numHoras;
	}
	public void setNumHoras(String numHoras) {
		this.numHoras = numHoras;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getPrecioOferta() {
		return precioOferta;
	}
	public void setPrecioOferta(String precioOferta) {
		this.precioOferta = precioOferta;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	private Date fechaFin;
	private String precio;
	private String precioOferta;
	private String modalidad;

}
