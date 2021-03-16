package com.hangman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.entities.UserModel;
import com.hangman.service.UserService;

@CrossOrigin(maxAge = 3600) //for CORS errors
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
	
}
