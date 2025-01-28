package com.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pojos.User;

public interface UserDao extends JpaRepository<User, Long>{
	
	

}
