package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> getOrdersByUserId(int user_id);
}
