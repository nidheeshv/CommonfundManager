package com.commonfundmanager.beans;

public class Login {
	private String username;
	private String password;
	private String role;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Login() {
		super();
	}
	public Login(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	

}
