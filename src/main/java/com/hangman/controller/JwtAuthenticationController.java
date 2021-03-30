package com.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.entities.JwtRequest;
import com.hangman.entities.JwtResponse;
import com.hangman.service.UserService;
import com.hangman.util.JwtTokenProvider;

//TODO: Maybe.  Integrate this into usercontroller
@RestController
@CrossOrigin //NB: using CorsMappings instead of a filter in SecurityConfig requires this annotation for our controllers
public class JwtAuthenticationController {
	
	@Autowired
	private UserService uServ;
	
	@Autowired
	private JwtTokenProvider jwtToken;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authRequest) throws Exception 
	{
		System.out.println(authRequest.toString());
		authRequest.getPassword();
		final UserDetails userDetails = uServ.loadUserByUsername(authRequest.getUsername());
		final String token = jwtToken.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/authenticate*", method = RequestMethod.POST)
	public ResponseEntity<?> badURLFallback(@RequestBody JwtRequest authRequest) throws Exception 
	{
		return new ResponseEntity<>("Malformed URL!", HttpStatus.BAD_REQUEST);
	
	}
	private void authenticate(String username, String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
