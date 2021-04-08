package com.hangman.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.hangman.entities.JwtRequest;
import com.hangman.entities.UserModel;
import com.hangman.service.UserService;
import com.hangman.util.JwtTokenProvider;

//TODO: why implement tests if that's harder than just manually testing the damn thing?
@RunWith(SpringRunner.class)
@WebMvcTest(JwtAuthenticationController.class)
public class JwtAuthenticationControllerTest {
	
	@MockBean
	private UserService uServ;
	
	@Autowired
	private JwtTokenProvider jwtToken;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Test
	public void whenMatchReturnToken() {
		
		JwtRequest jwtReq = new JwtRequest("testman", "password");
		UserModel user = new UserModel("testman", "password");
		UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities("USER").build(); 
		
		//given(uServ.loadUserByUsername(jwtReq.getUsername()).willReturn(userDetails);
//		
//		MvcResult response = mvc.perform(post("/authenticate")
//	    		  .param("username", "testman") //
//                  .param("password", "password") // 
//	    	      .contentType(MediaType.APPLICATION_JSON))
//	    	      .andExpect(status().isOk())
//	    	      .andReturn();
		
	}

}
