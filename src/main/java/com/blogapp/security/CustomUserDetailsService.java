package com.blogapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		Find if given user is present or not in our database
		User user = userRepository
				.findByUserEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Email Id : " + username, 0));
		
		return new UserDetail(user.getUserEmail(), user.getUserPassword(), user.getRoles());
	}

}
