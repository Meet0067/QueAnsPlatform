package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.User;
import com.Repository.UserRepository;


@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	public User validateuser(String email, String pass) {
		return  userRepository.findByEmailAndPassword(email,pass);
	}

	public long addUser(User user) {		
		return userRepository.save(user).getId();		
		
	}
	public Optional<User> findUserById(long id) {		
		return userRepository.findById(id);
		
	}
}
