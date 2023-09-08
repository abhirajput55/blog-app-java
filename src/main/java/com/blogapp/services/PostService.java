package com.blogapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapp.dto.PostDto;
import com.blogapp.dto.PostPageDto;
import com.blogapp.entities.Post;

@Service
public interface PostService {
	
	PostDto createPost(Post post);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getPostByUserId(Integer userId);
	
	List<PostDto> getPostByCategoryId(Integer categoryId);
	
	PostPageDto getAllPostsForPagingAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	List<PostDto> searchPostsByKeyword(String keyword);
	

}
