package com.hangman.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserModel { //Serializable ? 
	
	public UserModel() {
		super();
	}
	
	public UserModel(String username, String password) {
		this.username = username;
		this.password = password;
		this.winCount = 0;
	}
	
	//if no email is provided do the other one
	public UserModel(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.winCount = 0;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@SequenceGenerator(allocationSize = 1, name = "USER_SEQ", sequenceName = "SQ_USER")
	@Column(name = "USER_ID")
	private int ID;
	
	@Column(name="USERNAME", unique = true, nullable = false)
	private String username;
	
	@Column(name="EMAIL", unique = true)
	private String email;
	
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	//Count for number of puzzles solved while logged on
	@Column(name="WINCOUNT")
	private int winCount;
	
	//TODO: Add roles (mod/user distinction)

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (ID != other.ID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", username=" + username + ", email=" + email + ", winCount=" + winCount + "]";
	}
	

}
