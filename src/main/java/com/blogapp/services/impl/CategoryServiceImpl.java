package com.blogapp.services.impl;

import java.util.List;

import static com.blogapp.constants.AppConstants.CATEGORY;
import static com.blogapp.constants.AppConstants.ID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogapp.dto.CategoryDto;
import com.blogapp.entities.Category;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.repositories.CategoryRepository;
import com.blogapp.services.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
		
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
		
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryDescp(categoryDto.getCategoryDescp());
		Category save = categoryRepository.save(category);
		
		return modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
		
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		return categoryRepository.findAll()
				.stream()
				.map(category -> modelMapper.map(category, CategoryDto.class)).toList();
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
		
		categoryRepository.delete(category);
	}

}
