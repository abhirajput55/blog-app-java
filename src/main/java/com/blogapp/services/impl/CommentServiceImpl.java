package com.blogapp.services.impl;

import static com.blogapp.constants.AppConstants.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.blogapp.dto.CommentDto;
import com.blogapp.entities.Comments;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.CommentRepository;
import com.blogapp.repositories.PostRepository;
import com.blogapp.repositories.UserRepository;
import com.blogapp.services.CommentService;

@Component
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
		
		User user = userRepository
				.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
		Post post = postRepository
				.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
		
		Comments comments = modelMapper.map(commentDto, Comments.class);
		comments.setUser(user);
		comments.setPost(post);
		Comments comments2 = commentRepository.save(comments);
		
		return modelMapper.map(comments2, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comments comments = commentRepository
				.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException(COMMENT, ID, commentId));
		
		commentRepository.delete(comments);
	}

}
