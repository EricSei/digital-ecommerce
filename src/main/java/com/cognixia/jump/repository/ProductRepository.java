package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
