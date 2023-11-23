package com.espacio.bumeran.aula.model;

public class SignInCourses {
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String phone;
	private String pack;
	private boolean cuentos;
	private boolean vinculos;
	private boolean influencers;
	private boolean zapatos;
	private boolean limites;
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public boolean isCuentos() {
		return cuentos;
	}
	public void setCuentos(boolean cuentos) {
		this.cuentos = cuentos;
	}
	public boolean isVinculos() {
		return vinculos;
	}
	public void setVinculos(boolean vinculos) {
		this.vinculos = vinculos;
	}
	public boolean isInfluencers() {
		return influencers;
	}
	public void setInfluencers(boolean influencers) {
		this.influencers = influencers;
	}
	public boolean isZapatos() {
		return zapatos;
	}
	public void setZapatos(boolean zapatos) {
		this.zapatos = zapatos;
	}
	public boolean isLimites() {
		return limites;
	}
	public void setLimites(boolean limites) {
		this.limites = limites;
	}
	@Override
	public String toString() {
		return "SignInCourses [emailAddress=" + emailAddress + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phone=" + phone + ", pack=" + pack + ", cuentos=" + cuentos + ", vinculos=" + vinculos
				+ ", influencers=" + influencers + ", zapatos=" + zapatos + ", limites=" + limites + "]";
	}
	

}
