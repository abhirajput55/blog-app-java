package com.blogapp.utils;

import static com.blogapp.constants.AppConstants.CATEGORY;
import static com.blogapp.constants.AppConstants.ID;
import static com.blogapp.constants.AppConstants.USER;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogapp.dto.CategoryDto;
import com.blogapp.dto.CommentDto;
import com.blogapp.dto.PostDto;
import com.blogapp.dto.UserDto;
import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.CategoryRepository;
import com.blogapp.repositories.UserRepository;

@Component
public class ResourceUtil {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public Post toSavePost(PostDto postDto, Integer categoryId, Integer userId) {
		
		Post post = null;
		
		Category category = categoryRepository
				.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
		
		User user = userRepository
				.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
		try {
			post = modelMapper.map(postDto, Post.class);
			post.setPostDate(new Date());
			post.setCategory(category);
			post.setUser(user);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return post;
	}
	
	public PostDto postToPostDto(Post post) {
		
		PostDto postDto = null;
		try {
			CategoryDto categoryDto = modelMapper.map(post.getCategory(), CategoryDto.class);
			UserDto userDto = modelMapper.map(post.getUser(), UserDto.class);
			Set<CommentDto> commentDtos = new HashSet<>();
			post.getComments().forEach(e -> {
				commentDtos.add(modelMapper.map(e, CommentDto.class));
			});
			postDto = modelMapper.map(post, PostDto.class);
			
			postDto.setCategoryDto(categoryDto);
			postDto.setUserDto(userDto);
			postDto.setCommentDtos(commentDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return postDto;
	}

}
