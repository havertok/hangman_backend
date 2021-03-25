package com.hangman.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hangman.entities.UserModel;
import com.hangman.repos.UserStore;

@Service
public class UserRegistrationService {
	
	@Autowired
	private UserStore uStore;
	
	@Autowired
	private PasswordEncoder passwordEncoder; //set to Bcrypt in sec config
	
	public boolean doesUsernameExist(String username) {
		return uStore.existsByUsername(username);
	}
	
	public boolean doesEmailExist(String email) {
		return uStore.existsByEmail(email);
	}
	
	public UserModel getUserByName(String name) {
		Optional<UserModel> userDetails = uStore.findByUsername(name);
		if(userDetails.isPresent()) {
			return userDetails.get();
		} else {
			return null; //TODO: want to add more elegant error handling.
		}
	}
	
	public boolean addNewUser(UserModel user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); //we need to encrypt the password
		if(getUserByName(user.getUsername()) == null) {
			uStore.save(user);
			return true;
		} else {
			System.out.println("Username already exists!  Should have been caught in controller!"); //will implement a logger eventually
			return false;
		}
	}

}
