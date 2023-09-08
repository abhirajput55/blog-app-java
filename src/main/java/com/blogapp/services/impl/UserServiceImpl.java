package com.blogapp.services.impl;

import java.util.List;

import static com.blogapp.constants.AppConstants.USER;
import static com.blogapp.constants.AppConstants.ID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogapp.dto.UserDto;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.UserRepository;
import com.blogapp.services.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User saveUser = null;
		try {			
			saveUser = userRepository.save(modelMapper.map(userDto, User.class));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelMapper.map(saveUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		User updatedUser = null;
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(()-> new ResourceNotFoundException(USER, ID, userId));
			
			user.setUserName(userDto.getUserName());
			user.setUserEmail(userDto.getUserEmail());
			user.setUserPassword(userDto.getUserPassword());
			user.setUserAbout(userDto.getUserAbout());
			
			updatedUser = userRepository.save(user);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException(USER, ID, userId));
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		return userRepository.findAll()
				.stream()
				.map(user -> modelMapper.map(user, UserDto.class)).toList();
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(USER, ID, userId));
		
		userRepository.delete(user);
	}

}
