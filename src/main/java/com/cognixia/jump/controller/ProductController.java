package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;

@RequestMapping("/api/products/")
@RestController
public class ProductController {
	
	@Autowired
	ProductRepository repo;
	
	@GetMapping
	public List<Product> getProducts(){
		return repo.findAll();
	}
}
