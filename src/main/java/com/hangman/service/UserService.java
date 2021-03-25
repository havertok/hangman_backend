package com.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangman.entities.UserModel;
import com.hangman.repos.UserStore;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserStore userStore;
	
	public boolean doesEmailExist(String email) {
		return userStore.existsByEmail(email);
	}
	
	public boolean doesUsernameExit(String username) {
		return userStore.existsByUsername(username);
	}
	
	public UserModel getUserByName(String name) {
		Optional<UserModel> userDetails = userStore.findByUsername(name);
		if(userDetails.isPresent()) {
			return userDetails.get();
		} else {
			return null; //TODO: want to add more elegant error handling.
		}
	}
	
	public UserModel getUserByID(int ID) {
		Optional<UserModel> userDetails = userStore.findById(ID);
		if(userDetails.isPresent()) {
			return userDetails.get();
		} else {
			return null; //TODO: want to add more elegant error handling.
		}
	}
	
	public String getUsernameByID(int ID) {
		Optional<UserModel> userDetails = userStore.findById(ID);
		if(userDetails.isPresent()) {
			return userDetails.get().getUsername();
		} else {
			return "No user of that id found!"; //TODO: want to add more elegant error handling.
		}
	}
	
	public String getUserPasswordByName(String name) {
		try {
			UserModel user = getUserByName(name);
			return user.getPassword();
		} catch(Exception e) {
			System.out.println("Problem getting user by name!\n" + e);
			return "NO SUCH USER!";
		}
	}
	
	public List<UserModel> getAllUsers(){
		
		ArrayList<UserModel> temp = new ArrayList<>();
		try {
			for(Object o : userStore.findAll()) {
				if(o instanceof UserModel) {
					temp.add((UserModel)o);
				}
			}
		} catch(Exception err){
			System.out.println("Something went wrong fetching all users");
			System.out.println(err);
		}
		
		return temp;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build(); //Custom UserDetails
        return userDetails;
	}
	

}
