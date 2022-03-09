package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return repo.findAll();
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user){
		user.setId(null);
		// use a password encoder to encode the password so it is not saved as plain text in the database
		user.setPassword(encoder.encode(user.getPassword()));
		
		User created = repo.save(user);
		
		return ResponseEntity.status(201).body(created);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) throws ResourceNotFoundException{

		String requestPassword = encoder.encode(user.getPassword());
		
		Optional<User> found = repo.findByUsername(user.getUsername());
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("user name not found");
		}
		
		User savedUser = found.get();
		//System.out.println("saved pw: "+savedUser.getPassword());
		if( requestPassword.equals(savedUser.getPassword())) {
			return null;
		}
		return ResponseEntity.status(201).body(savedUser);
		
	}
	
	@PostMapping("/users/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody User user){
		user.setId(null);
		// use a password encoder to encode the password so it is not saved as plain text in the database
		user.setPassword(encoder.encode(user.getPassword()));
		
		User created = repo.save(user);
		
		return ResponseEntity.status(201).body(created);
	}
}
