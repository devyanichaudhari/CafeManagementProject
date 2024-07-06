package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Recipe;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt=userRepository.findById(userId);
		if(opt.isPresent()) 
		{
			return opt.get();
		}
		throw new Exception("User Not Found with Id : "+userId);
	}

}
