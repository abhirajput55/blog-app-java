package com.blogapp.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.blogapp.constants.ApiURI.*;
import static com.blogapp.constants.ApiConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.UserDto;
import com.blogapp.exceptions.ApiResponse;
import com.blogapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(BASE_URL + USER)
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@PostMapping(CREATE_USER)
	public ResponseEntity<Map<String, Object>> createNewUser(@Valid @RequestBody UserDto userDto){
		Map<String, Object> map = new HashMap<>();
		
		UserDto createUser = userService.createUser(userDto);
		if(Objects.nonNull(createUser)) {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_ADDED);
			map.put(DATA, createUser);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		}else {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_ADDING_DATA);			
			return ResponseEntity.ok(map);
		}
	}
	
	@PutMapping(UPDATE_USER)
	public ResponseEntity<Map<String, Object>> updateNewUser(@Valid @PathVariable Integer userId,@RequestBody UserDto userDto){
		Map<String, Object> map = new HashMap<>();
		
		UserDto updateUser = userService.updateUser(userId,userDto);
		if(Objects.nonNull(updateUser)) {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_UPDATED);
			map.put(DATA, updateUser);				
		}else {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_UPDATE_DATA);			
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(GET_USER_BY_ID)
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Integer userId){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_FOUND);
		map.put(DATA, userService.getUserById(userId));	
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(GET_ALL_USER)
	public ResponseEntity<Map<String, Object>> getAllUsers(){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_FOUND);
		map.put(DATA, userService.getAllUsers());
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping(DELETE_USER)
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId){
		
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse(true, RECORD_DELETED), HttpStatus.OK);
	}
	

}