package com.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
