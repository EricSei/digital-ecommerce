package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return  userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user){
		
		User created =userService.createUser(user);
		
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user){
		
		
		User updated = userService.updateUser(user);
		if(updated == null) {
			return ResponseEntity
					.status(401)
					.body(user.getId()+ "can not updated or found");
		}
		
		return ResponseEntity
				.status(201)
				.body(updated);
		
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
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable int id){
		
		if(userService.deleteUserById(id)) {
			return ResponseEntity
					.status(201)
					.body(id + "was deleted");
		}
		return ResponseEntity
				.status(401)
				.body(id + "was not found, or can't be deleted.");
	}
	
}
