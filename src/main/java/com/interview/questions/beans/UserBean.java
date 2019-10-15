package com.interview.questions.beans;

import java.io.Serializable;

public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1796993515097060780L;

	private String firstName;
	
	private String lastName;
	
	private String role;
	
	private String password;
	
	private String mail;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
