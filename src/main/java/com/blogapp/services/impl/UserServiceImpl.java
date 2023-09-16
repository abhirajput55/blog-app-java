package com.blogapp.services.impl;

import static com.blogapp.constants.AppConstants.ID;
import static com.blogapp.constants.AppConstants.ROLE;
import static com.blogapp.constants.AppConstants.USER;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blogapp.constants.AppConstants;
import com.blogapp.dto.UserDto;
import com.blogapp.entities.Role;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.RoleRepository;
import com.blogapp.repositories.UserRepository;
import com.blogapp.services.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		UserDto saveUser = null;
		Integer roleId = AppConstants.NORMAL_ROLE;
		try {
			if(Objects.nonNull(userDto)) {
				if(userDto.getRoleChar() == 'A' || userDto.getRoleChar() == 'a') {
					roleId = AppConstants.ADMIN_ROLE;
				}
				else if(userDto.getRoleChar() == 'C' || userDto.getRoleChar() == 'c') {
					roleId = AppConstants.CUSTOMER_ROLE;
				}
				userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
			}
			Role role = roleRepository.findById(roleId)
					.orElseThrow(() -> new ResourceNotFoundException(ROLE, userDto.getRoleChar().toString(), 0));				

			User user = modelMapper.map(userDto, User.class);
			user.getRoles().add(role);
			saveUser = modelMapper.map(userRepository.save(user), UserDto.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return saveUser;
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto userDto) {
		User updatedUser = null;
		Integer roleId = AppConstants.NORMAL_ROLE;
		try {
			User user = userRepository.findById(userId)
					.orElseThrow(()-> new ResourceNotFoundException(USER, ID, userId));
			
			if(Objects.nonNull(userDto)) {
				if(userDto.getRoleChar() == 'A' || userDto.getRoleChar() == 'a') {
					roleId = AppConstants.ADMIN_ROLE;
				}
				else if(userDto.getRoleChar() == 'C' || userDto.getRoleChar() == 'c') {
					roleId = AppConstants.CUSTOMER_ROLE;
				}
				Role role = roleRepository.findById(roleId)
						.orElseThrow(() -> new ResourceNotFoundException(ROLE, userDto.getRoleChar().toString(), 0));
				
				user.setUserName(userDto.getUserName());
				user.setUserEmail(userDto.getUserEmail());
				user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
				user.setUserAbout(userDto.getUserAbout());
				user.getRoles().add(role);
				updatedUser = userRepository.save(user);			
			}
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
