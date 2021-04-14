package com.hangman.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtEntry;
	
	@Autowired
	private JwtRequestFilter jwtFilter;
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		 DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
		 provider.setPasswordEncoder(passwordEncoder());
		 provider.setUserDetailsService(myUserDetailsService);
		 return provider;
		}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	  
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	  
//	  @Bean
//	  @Order(Ordered.HIGHEST_PRECEDENCE) 
//	  public CorsFilter corsFilter() {
//	      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	      final CorsConfiguration config = new CorsConfiguration();
//	      config.setAllowCredentials(true);
//	      //"http://localhost:3000" is what did it, I will have to read up on url filtering syntax
//	      config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3000/Home", "http://localhost:3000/Login"));
//	      config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Access-Control-Allow-Origin", "X-Auth-Token"));
//	      //config.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Request-Allow-Origin", "Access-Control-Allow-Credentials"));
//	      config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//	      source.registerCorsConfiguration("/**", config);
//	      return new CorsFilter(source);
//	  }
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
	  
//	public void addCorsMappings(CorsRegistry registry) {
//		 registry.addMapping("/**").allowedOrigins("*")
//		 	.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH").allowedHeaders("*");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http
	 //.addFilterBefore(corsFilter(), SessionManagementFilter.class)
		.cors().and()
		.csrf().disable() //another attempt to fix cors
		.authorizeRequests()
		.antMatchers("/puzzles/all", "/puzzles/modify", "/tregister", "/user/all", "/register", "/authenticate").permitAll() //puzzles/modify and /user/all will eventually be blocked /tregister is the builtin registration form for testing
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //Should allow cors preflight (options) requests
		.anyRequest().authenticated() 
		.and()
		.logout() 
		.permitAll()
	 	.and()
	 	.exceptionHandling().authenticationEntryPoint(jwtEntry).and().sessionManagement()
	 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 //.httpBasic(); 
	}
	

	
}
