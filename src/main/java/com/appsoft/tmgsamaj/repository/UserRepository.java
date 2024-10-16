package com.appsoft.tmgsamaj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsoft.tmgsamaj.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}
