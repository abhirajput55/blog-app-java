package com.blogapp.services.impl;

import static com.blogapp.constants.AppConstants.CATEGORY;
import static com.blogapp.constants.AppConstants.ID;
import static com.blogapp.constants.AppConstants.POST;
import static com.blogapp.constants.AppConstants.USER;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.blogapp.constants.AppConstants;
import com.blogapp.dto.PostDto;
import com.blogapp.dto.PostPageDto;
import com.blogapp.entities.Post;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.CategoryRepository;
import com.blogapp.repositories.PostRepository;
import com.blogapp.repositories.UserRepository;
import com.blogapp.services.PostService;
import com.blogapp.utils.ResourceUtil;

@Component
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ResourceUtil resourceUtil;

	@Override
	public PostDto createPost(Post post) {
		Post save = postRepository.save(post);
		return resourceUtil.postToPostDto(save);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage(postDto.getPostImage());
		Post save = postRepository.save(post);
		return resourceUtil.postToPostDto(save);
	}

	@Override
	public List<PostDto> getAllPosts() {
		
		return postRepository
				.findAll()
				.stream()
				.map(e -> resourceUtil.postToPostDto(e)).toList();
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
		
		return resourceUtil.postToPostDto(post);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(POST, ID, postId));
		postRepository.delete(post);
	}

	@Override
	public List<PostDto> getPostByUserId(Integer userId) {
		
		userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, ID, userId));
		
		return postRepository
				.findByUserUserId(userId)
				.stream()
				.map(e -> resourceUtil.postToPostDto(e)).toList();
	}

	@Override
	public List<PostDto> getPostByCategoryId(Integer categoryId) {
		
		categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
		
		return postRepository
				.findByCategoryCategoryId(categoryId)
				.stream()
				.map(e -> resourceUtil.postToPostDto(e)).toList();
	}

	@Override
	public PostPageDto getAllPostsForPagingAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		
		PostPageDto pageDto = new PostPageDto();
		
		Sort sort = (sortDirection.equalsIgnoreCase(AppConstants.DECENDING))?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);		
		
		Page<Post> page = postRepository.findAll(pageable);
		
		pageDto.setContent(page.getContent().stream().map(e -> resourceUtil.postToPostDto(e)).toList());
		pageDto.setCurrentPage(page.getNumber());
		pageDto.setPageSize(page.getSize());
		pageDto.setTotalElements(page.getNumberOfElements());
		pageDto.setTotalPages(page.getTotalPages());
		pageDto.setFirstPage(page.isFirst());
		pageDto.setLastPage(page.isLast());
		
		return pageDto;
	}

	@Override
	public List<PostDto> searchPostsByKeyword(String keyword) {
		
		return postRepository
				.findByPostTitleContaining(keyword)
				.stream()
				.map(e -> resourceUtil.postToPostDto(e)).toList();
	}

}
