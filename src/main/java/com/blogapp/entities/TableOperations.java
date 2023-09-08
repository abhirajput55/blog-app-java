package com.blogapp.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableOperations {
	
	@Column(name = "created_by")
	int createdBy;
	
	@Column(name = "created_date")
	String createdDate;
	
	@Column(name = "updated_by")
	int updatedBy;
	
	@Column(name = "updated_date")
	String updatedDate;
	
	@Column(name = "deleted_by")
	int deletedBy;
	
	@Column(name = "deleted_date")
	String deletedDate;

}
