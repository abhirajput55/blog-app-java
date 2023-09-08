package com.blogapp.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	
	@NotEmpty(message = "Post Title should not be empty.")
	@Size(min = 4, max = 50, message = "Title size must be in between 4 to 12 characters only.")
	private String postTitle;
	
	@NotEmpty(message = "Post Content should not be empty.")
	@Size(max = 500, message = "Post Content character length exceded.")
	private String postContent;
	
	private String postImage;
	
	private Date postDate;
	
	private CategoryDto categoryDto;
	
	private UserDto userDto;

}
