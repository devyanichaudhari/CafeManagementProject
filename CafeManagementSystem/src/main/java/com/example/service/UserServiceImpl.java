package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.config.JwtProvider;
import com.example.entity.Recipe;
import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt=userRepository.findById(userId);
		if(opt.isPresent()) 
		{
			return opt.get();
		}
		throw new Exception("User Not Found with Id : "+userId);
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		if(email==null)
		{
			throw new Exception("Provide valid jwt token!");
		}
		User user=userRepository.findByEmail(email);
		if(user==null)
		{
			throw new Exception("User Not Found with Email! "+email);
		}
		return user;
	}

}
