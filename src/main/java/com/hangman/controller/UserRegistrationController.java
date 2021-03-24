package com.hangman.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hangman.entities.UserModel;
import com.hangman.entities.UserModelDTO;
import com.hangman.service.UserRegistrationService;

@Controller
@CrossOrigin
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationService urServ;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<String> postNewUser(@RequestBody UserModelDTO userDTO) {
		UserModel newuser = new UserModel();
		if(userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
			newuser.setUsername(userDTO.getUsername());
			newuser.setPassword(userDTO.getPassword()); //we need to hash this
			if(userDTO.getEmail() != null) {
				newuser.setEmail(userDTO.getEmail());
			}
		}
		if(newuser.getUsername() == null) {
			return new ResponseEntity<>("User object malformed.", HttpStatus.BAD_REQUEST);
		}
		if(urServ.doesEmailExist(newuser.getEmail()) || urServ.doesUsernameExist(newuser.getUsername())) {
			return new ResponseEntity<>("Username or Email already exists.", HttpStatus.BAD_REQUEST);
		} else {
			if(urServ.addNewUser(newuser)) {
				return new ResponseEntity<>("User added!", HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>("We got it, but something went wrong!", HttpStatus.ACCEPTED);
		}
	}
	
	
	//Template based Controllers go BELOW, endpoints prefixed with 't' to denote that these are accessed via the server directly and NOT through our frontend.
	//=======================================================================================================================================================
	
	@RequestMapping(value="/tregister", method=RequestMethod.GET)
	public String getRegistrationPage(Model model) {
	    UserModelDTO userDto = new UserModelDTO();
	    model.addAttribute("user", userDto);
		return "registrationPage";
	}
	
	//public ResponseEntity<AppResponse> addUser(@RequestBody Map<String, Object> lookupRequestObject) 
	@RequestMapping(value="/tregister", method=RequestMethod.POST)
	public ResponseEntity<String> submitUserDetails(@ModelAttribute("user") UserModelDTO user, BindingResult bindingResult, HttpServletRequest request, Errors errors){
		//TODO:  Password does not encrypt when posting data.  How to fix?
		UserModel newuser = new UserModel(user.getUsername(), user.getPassword(), user.getEmail());
		if(urServ.doesEmailExist(newuser.getEmail()) || urServ.doesUsernameExist(newuser.getUsername())) {
			return new ResponseEntity<>("Username or Email already exists.", HttpStatus.BAD_REQUEST);
		} else {
			if(urServ.addNewUser(newuser)) {
				return new ResponseEntity<>("User added!", HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>("We got it, but something went wrong!", HttpStatus.ACCEPTED);
		}
	}

}
