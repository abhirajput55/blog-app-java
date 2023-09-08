package com.blogapp.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostPageDto {
	
	private List<PostDto> content;
	
	private Integer currentPage;
	
	private Integer pageSize;
	
	private Integer totalPages;
	
	private Integer totalElements;
	
	private boolean isFirstPage;

	private boolean isLastPage;
}
