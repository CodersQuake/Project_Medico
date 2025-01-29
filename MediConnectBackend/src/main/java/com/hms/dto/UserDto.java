package com.hms.dto;

import com.hms.pojos.User_role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class UserDto {
	
	
	private String Id;
   
	private String userName;
	
	private String userEmail;
	
	private String password;
	
	private String phone_no;
	
	private User_role user_role;
	
}
