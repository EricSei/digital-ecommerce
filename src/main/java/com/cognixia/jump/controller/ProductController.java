package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;

@RequestMapping("/api")
@RestController
public class ProductController {
	
	@Autowired
	ProductRepository repo;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return repo.findAll();
	}
	
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		
		Product added = repo.save(product);
		
		return new ResponseEntity<>(added, HttpStatus.CREATED);
	}
	
	
}
