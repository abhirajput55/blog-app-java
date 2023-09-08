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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.constants.AppConstants;
import com.blogapp.dto.PostDto;
import com.blogapp.services.PostService;
import com.blogapp.utils.ResourceUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping(BASE_URL + POST)
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	ResourceUtil resourceUtil;
	
	@PostMapping(CREATE_POST)
	public ResponseEntity<Map<String, Object>> createNewPost(@Valid @RequestBody PostDto postDto, 
			@PathVariable Integer categoryId,
			@PathVariable Integer userId) {
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_ADDED);
		map.put(DATA, postService.createPost(resourceUtil.toSavePost(postDto, categoryId, userId)));
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@PutMapping(UPDATE_POST)
	public ResponseEntity<Map<String, Object>> updatePost(@Valid @RequestBody PostDto postDto, 
			@PathVariable Integer postId){
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_UPDATED);
			map.put(DATA, postService.updatePost(postDto, postId));
		} catch (Exception e) {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_UPDATE_DATA);
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(GET_ALL_POSTS)
	public ResponseEntity<Map<String, Object>> getAllPosts(){
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_FOUND);
			map.put(DATA, postService.getAllPosts());
		} catch (Exception e) {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_FETCHING_DATA);
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(GET_POST_BY_ID)
	public ResponseEntity<Map<String, Object>> getPostByPostId(@PathVariable Integer postId){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_FOUND);
		map.put(DATA, postService.getPostById(postId));
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(CATEGORY_POSTS)
	public ResponseEntity<Map<String, Object>> getPostByCategory(@PathVariable Integer categoryId){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_FOUND);
		map.put(DATA, postService.getPostByCategoryId(categoryId));
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(USER_POSTS)
	public ResponseEntity<Map<String, Object>> getPostByUser(@PathVariable Integer userId){
		
		Map<String, Object> map = new HashMap<>();
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_FOUND);
		map.put(DATA, postService.getPostByUserId(userId));
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping(DELETE_POST)
	public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Integer postId){
		
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_DELETED);
		} catch (Exception e) {
			map.put(SUCCESS, true);
			map.put(MESSAGE, ERROR_WHILE_DELETING_DATA);
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(PAGINATION)
	public ResponseEntity<Map<String, Object>> getAllPostsByPagination(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection){
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_FOUND);
			map.put(DATA, postService.getAllPostsForPagingAndSorting(pageNumber, pageSize, sortBy, sortDirection));
		} catch (Exception e) {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_FETCHING_DATA);
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping(SEARCH_POST)
	public ResponseEntity<Map<String, Object>> getAllPostsByPagination(@PathVariable String keyword){
		Map<String, Object> map = new HashMap<>();
		try {
			map.put(SUCCESS, true);
			map.put(MESSAGE, RECORD_FOUND);
			map.put(DATA, postService.searchPostsByKeyword(keyword));
		} catch (Exception e) {
			map.put(SUCCESS, false);
			map.put(MESSAGE, ERROR_WHILE_FETCHING_DATA);
		}
		return ResponseEntity.ok(map);
	}

}
