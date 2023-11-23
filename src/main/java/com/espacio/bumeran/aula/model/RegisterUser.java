package com.espacio.bumeran.aula.model;

public class RegisterUser {
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String password;
	

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "RegisterUser [emailAddress=" + emailAddress + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + "]";
	}
	
	
}
