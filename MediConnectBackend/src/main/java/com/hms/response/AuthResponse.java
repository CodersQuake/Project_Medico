package com.hms.response;



import com.hms.pojos.User_role;

import lombok.Data;

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	private User_role role;
	


}
