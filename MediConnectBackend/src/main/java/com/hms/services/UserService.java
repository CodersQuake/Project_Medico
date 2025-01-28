package com.hms.services;

import java.util.List;

import com.hms.dto.UserDto;

public interface UserService {
	
	String registerUser(UserDto user) ;
	
	String deleteUser(Long userid) ;
	
//    String updatepassword(UserDto user);

	List<UserDto> getallUser();
	
	UserDto getUserByUserid(Long userid);
	
	


	



}
