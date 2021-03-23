package com.hangman.config;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
	 * AUTO GENERATED UID
	 */
	private static final long serialVersionUID = -4526235746122171471L;

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException 
    {
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
