package com.blogapp.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
	@Size(min = 4, max = 8, message = "Please set your password between 4 to 8 characters.")
	private String userPassword;
	
	@Size(max = 50, message = "You can add your summary upto 50 characters only.")
	private String userAbout;

}
