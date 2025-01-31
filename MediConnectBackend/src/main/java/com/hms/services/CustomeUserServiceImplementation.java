package com.hms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hms.dao.UserDao;
import com.hms.pojos.User;
import com.hms.pojos.User_role;

@Service
public class CustomeUserServiceImplementation implements UserDetailsService {
	
	private UserDao userRepository;
	
	public CustomeUserServiceImplementation(UserDao userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		User user = userRepository.findByuserEmail(username);
		
		if(user==null) {

			throw new UsernameNotFoundException("user not found with email  - "+username);
		}
		
		User_role role=user.getUser_role();
		
		if(role==null) role=User_role.PATIENT;
		
		System.out.println("role  ---- "+role);
		
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(role.toString()));
		
		return new org.springframework.security.core.userdetails.User(
				user.getUserEmail(),user.getPassword(),authorities);
	}

}
