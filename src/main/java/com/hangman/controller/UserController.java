package com.hangman.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.entities.UserModel;
import com.hangman.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uServ;

	//TODO: Remove this when done testing
	@RequestMapping(value="/all", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserModel>> getAllUsers(){
		return new ResponseEntity<>(uServ.getAllUsers(), HttpStatus.OK);
	}
	
	//returns a string for now, should eventually return java token or whatever
	@RequestMapping(value="/login", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getLogin(@RequestBody Map<String, String> inputData){
		String usernameI, passwordI;
		if(inputData != null) {
			if(inputData.containsKey("username") && inputData.containsKey("password")) {
				UserModel newuser = new UserModel(inputData.get("username"), inputData.get("password"));
			} else {
				return new ResponseEntity<>("Malformed user object sent!", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("Empty payload sent!", HttpStatus.BAD_REQUEST);
		}
		//TODO: Need to setup tokens and stuff here
		//this is where we will actually authenticate, on hold for now
		return new ResponseEntity<>("You are logged in!", HttpStatus.OK);
	}
	
}
