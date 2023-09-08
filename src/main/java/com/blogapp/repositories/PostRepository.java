package com.blogapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.dto.PostDto;
import com.blogapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByCategoryCategoryId(Integer categoryId);
	
	List<Post> findByUserUserId(Integer categoryId);

	List<Post> findByPostTitleContaining(String keyword);

}
