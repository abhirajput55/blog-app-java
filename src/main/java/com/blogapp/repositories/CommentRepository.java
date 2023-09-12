package com.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entities.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

}
