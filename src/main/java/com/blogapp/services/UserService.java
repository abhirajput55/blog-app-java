package com.blogapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapp.dto.UserDto;

@Service
public interface UserService {
	
	UserDto registerNewUser(UserDto userDto);
	
	UserDto updateUser(Integer userId, UserDto userDto);
	
	UserDto getUserById(Integer  userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);	

}
