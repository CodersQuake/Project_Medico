package com.hms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.User;

public interface UserDao extends JpaRepository<User, Long>{


	User findByuserEmail(String username);
	
	

}
