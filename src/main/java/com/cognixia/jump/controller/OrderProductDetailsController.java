
package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.OrderProductDetails;
import com.cognixia.jump.repository.OrderProductDetailsRepository;

@RequestMapping("/api/orders/products/")
@RestController
public class OrderProductDetailsController {
	
	@Autowired
	OrderProductDetailsRepository repo;
	
	@GetMapping
	public List<OrderProductDetails> getOrderProducts(){
		return repo.findAll();
	}
}

