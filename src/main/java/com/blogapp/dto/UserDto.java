package com.blogapp.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Integer userId;
	
	@NotEmpty(message = "Username should not be empty.")
	private String userName;
	
	@Email(message = "Please set a valid email id.")
	private String userEmail;
	
	@NotEmpty
	@Size(min = 4, max = 12, message = "Please set your password between 4 to 12 characters.")
//	Tis property may only be set as part of deserialization (using "setter" method, 
//	or assigning to Field, or passed as Creator argument) but will not be get for serialization
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;
	
	@Size(max = 50, message = "You can add your summary upto 50 characters only.")
	private String userAbout;
	
	private Set<RoleDto> roles = new HashSet<>();

	private Character roleChar;
}
