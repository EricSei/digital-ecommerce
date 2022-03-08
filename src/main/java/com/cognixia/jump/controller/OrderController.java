package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.OrderRepository;


@RequestMapping("/api/orders/")
@RestController
public class OrderController {
	
	@Autowired
	OrderRepository repo;
	
	@GetMapping
	public List<Order> getOrders(){
		return repo.findAll();
	}
}
