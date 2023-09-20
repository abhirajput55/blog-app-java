package com.blogapp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blogapp.constants.AppConstants;
import com.blogapp.entities.Role;
import com.blogapp.repositories.RoleRepository;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
		
		
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


//	If roles are not present, this method create roles in database first
	@Override
	public void run(String... args) throws Exception {
	
		try {
			Role role1 = new Role();
			role1.setRoleId(AppConstants.NORMAL_ROLE);
			role1.setRoleName(AppConstants.USER);
			Role role2 = new Role();
			role2.setRoleId(AppConstants.ADMIN_ROLE);
			role2.setRoleName(AppConstants.ADMIN);
			Role role3 = new Role();
			role3.setRoleId(AppConstants.CUSTOMER_ROLE);
			role3.setRoleName(AppConstants.CUSTOMER);
			
			List<Role> roleList = List.of(role1, role2, role3);
			roleRepository.saveAll(roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
