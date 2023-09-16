package com.blogapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, max = 15, message = "Title length should be in between 4 to 15 characters only.")
	private String categoryName;
	
	@NotEmpty(message = "Description should not be empty.")
	private String categoryDescp;

}
