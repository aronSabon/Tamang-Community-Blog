package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}
