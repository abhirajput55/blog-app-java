package com.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
