package com.hangman.entities;

import java.io.Serializable;

//Similar to the UserModelDTO, this pojo 
public class JwtRequest implements Serializable {
	
	/**
	 * auto generated
	 */
	private static final long serialVersionUID = 8743554988068919306L;
	private String username;
	private String password;

	public JwtRequest() {

	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}