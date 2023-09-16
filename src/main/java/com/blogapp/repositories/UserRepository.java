package com.blogapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserEmail(String username);

}
