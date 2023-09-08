package com.blogapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TableOperationsDto {
	
	private int createdBy;
	
	private String createdDate;
	
	private int updatedBy;
	
	private String updatedDate;
	
	private int deletedBy;
	
	private String deletedDate;

}
