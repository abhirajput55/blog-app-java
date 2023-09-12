package com.blogapp.controllers;

import static com.blogapp.constants.ApiURI.*;
import static com.blogapp.constants.ApiConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.CommentDto;
import com.blogapp.services.CommentService;

@RestController
@RequestMapping(BASE_URL + COMMENT)
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping(CREATE_COMMENT)
	public ResponseEntity<Map<String, Object>> createNewComment(
			@RequestBody CommentDto commentDto,
			@PathVariable Integer userId, 
			@PathVariable Integer postId){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_ADDED);
		map.put(DATA, commentService.createComment(commentDto, userId, postId));
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
	
	@PostMapping(DELETE_COMMENT)
	public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Integer commentId){
		
		Map<String, Object> map = new HashMap<>();
		commentService.deleteComment(commentId);
		map.put(SUCCESS, true);
		map.put(MESSAGE, RECORD_DELETED);
		
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

}
