package com.blogapp.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtAuthRequest {
	
	private String username;
	
	private String password;

}
