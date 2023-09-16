package com.blogapp.controllers;

import static com.blogapp.constants.ApiURI.AUTH;
import static com.blogapp.constants.ApiURI.BASE_URL;
import static com.blogapp.constants.ApiURI.LOGIN;
import static com.blogapp.constants.AppConstants.TOKEN;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.exceptions.ApiException;
import com.blogapp.security.CustomUserDetailsService;
import com.blogapp.security.JwtAuthRequest;
import com.blogapp.utils.JwtTokenUtil;

@RestController
@RequestMapping(BASE_URL + AUTH)
public class AuthenticationController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(LOGIN)
	public ResponseEntity<Map<String, String>> loginMethod(@RequestBody JwtAuthRequest jwtAuthRequest) throws ApiException{
		
		Map<String, String> map = new HashMap<>();
		
//		First authenticate user by using Authentication Object 
		this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
		
		
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		
		String token = jwtTokenUtil.generateToken(userDetails);
		map.put(TOKEN, token);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}


	private void authenticate(String username, String password) throws ApiException {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			this.authenticationManager.authenticate(authenticationToken);			
		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid Creads");
		}
		
	}

}
