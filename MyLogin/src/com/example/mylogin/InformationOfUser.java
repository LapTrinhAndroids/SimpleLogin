package com.example.mylogin;

public class InformationOfUser {
	private String lastname;
	private String firstname;
	private String email;
	private String password;
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public InformationOfUser() {
		super();
	}
	public InformationOfUser(String lastname, String firstname, String email, String password) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
	}
	
}
