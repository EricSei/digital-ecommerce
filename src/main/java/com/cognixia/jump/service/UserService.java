package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public Optional<User> getUserByid(int id){
		
		Optional<User> found = userRepo.findById(id);
		return found;
	}
	
	
	public User createUser(User user) {
		
		user.setId(null);
		// use a password encoder to encode the password so it is not saved as plain text in the database
		user.setPassword(encoder.encode(user.getPassword()));
		
		User created = userRepo.save(user);
		
		
		return created;
	}
	
	
	public User updateUser(User user) {
		
		if(userRepo.existsById(user.getId())) {
			user.setPassword(encoder.encode(user.getPassword()));
			User updated = userRepo.save(user);
			return updated;
			
		}
		
		return null;
		
	}
	
	
	public boolean deleteUserById(int id){
		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	//
	
	
	
	
	
	
	
	
	

}
