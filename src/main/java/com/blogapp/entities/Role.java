package com.blogapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_master")
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Integer roleId;
	
	@Column(name = "role_name")
	private String roleName;
	
//	@ManyToMany(mappedBy = "roles")
//	private Set<User> users = new HashSet<>();

}
