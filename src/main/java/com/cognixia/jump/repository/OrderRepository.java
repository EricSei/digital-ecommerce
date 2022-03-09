package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.cognixia.jump.model.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> getOrdersByUserId(int user_id);
	
	@Query("select o from Order o where o.status = ?1 ")
	public List<Order> getOrdersFromStatus(String status);
	
}
