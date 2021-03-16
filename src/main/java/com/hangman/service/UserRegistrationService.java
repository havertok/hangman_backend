package com.hangman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangman.entities.UserModel;
import com.hangman.repos.UserStore;

@Service
public class UserRegistrationService {
	
	@Autowired
	private UserStore uStore;
	
	public boolean doesUsernameExist(String username) {
		return uStore.existsByUsername(username);
	}
	
	public boolean doesEmailExist(String email) {
		return uStore.existsByEmail(email);
	}
	
	public boolean addNewUser(UserModel user) {
		try {
			uStore.save(user);
			return true;
		} catch (Exception err) {
			System.out.println(err);
		}
		return false;
	}

}
