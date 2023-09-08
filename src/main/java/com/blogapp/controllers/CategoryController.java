package com.blogapp.controllers;

import static com.blogapp.constants.ApiURI.*;
import static com.blogapp.constants.ApiConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.CategoryDto;
import com.blogapp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(BASE_URL + CATEGORY)
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping(CREATE_CATEGORY)
	public ResponseEntity<Map<String, Object>> createNewCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_ADDED);
		map.put(DATA, categoryService.createCategory(categoryDto));			
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@PutMapping(UPDATE_CATEGORY)
	public ResponseEntity<Map<String, Object>> updateOldCategory(@Valid @PathVariable Integer categoryId ,@RequestBody CategoryDto categoryDto){
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_UPDATED);
			map.put(DATA, categoryService.updateCategory(categoryId, categoryDto));			
		} catch (Exception e) {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_UPDATE_DATA);
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(GET_CATEGORY_BY_ID)
	public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable Integer categoryId){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_FOUND);
		map.put(DATA, categoryService.getCategoryById(categoryId));	
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(GET_ALL_CATEGORY)
	public ResponseEntity<Map<String, Object>> getAllCategory(){
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_FOUND);
			map.put(DATA, categoryService.getAllCategories());		
		} catch (Exception e) {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_FETCHING_DATA);
		}
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping(DELETE_CATEGORY)
	public ResponseEntity<Map<String, Object>> getAllCategory(@PathVariable Integer categoryId){
		
		Map<String, Object> map = new HashMap<>();
		categoryService.deleteCategory(categoryId);
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_DELETED);
		return ResponseEntity.ok(map);
	}

}
