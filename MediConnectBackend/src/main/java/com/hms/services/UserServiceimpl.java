package com.hms.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dao.UserDao;
import com.hms.dto.UserDto;
import com.hms.pojos.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceimpl  implements UserService{
	
	@Autowired
	private UserDao userdao;
	@Autowired
	private ModelMapper mapper;
  

	@Override
	public String registerUser(UserDto user) {
		// TODO Auto-generated method stub
		
		User u = userdao.save(mapper.map(user, User.class)) ;
		
		return "user register succesfully with id"+u.getId();
	 
	}

	@Override
	public String deleteUser(Long userid) {
		

		if(userdao.findById(userid)!=null) {
			
			userdao.deleteById(userid);
			
		}
		else {
			
			throw new com.hms.exceptions.NoResourceFoundException("No User is avilable for this id") ;
		}
		
		return "user delete succesfully !" ;
		
		
	}

	@Override
	public List<UserDto> getallUser() {
		// TODO Auto-generated method stub
		
		List<UserDto> u = userdao.findAll().stream()
				  .map(user->mapper.map(user, UserDto.class))
				  .collect(Collectors.toList()) ;
		
		
		return u;
	}

	@Override
	public UserDto getUserByUserid(Long userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
