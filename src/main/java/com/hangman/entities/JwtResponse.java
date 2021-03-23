package com.hangman.entities;

import java.io.Serializable;

//A custom response object for storing a web token
public class JwtResponse implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5494096700485608122L;
	
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}