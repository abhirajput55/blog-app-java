package com.blogapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapp.dto.CategoryDto;

@Service
public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto);
	
	CategoryDto getCategoryById(Integer categoryId);
	
	List<CategoryDto> getAllCategories();
	
	void deleteCategory(Integer categoryId);

}
