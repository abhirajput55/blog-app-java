package com.blogapp.services;

import org.springframework.stereotype.Service;

import com.blogapp.dto.CommentDto;

@Service
public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);
	
	void deleteComment(Integer commentId);

}
