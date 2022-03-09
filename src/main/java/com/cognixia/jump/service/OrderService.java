package com.cognixia.jump.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.OrderProductDetails;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.util.JwtUtil;

@Service
public class OrderService {
	
	
	//combine userrepo, orderrepo
	// generateOrder
	//get user ojbect from controller 
	//pass it to createOrder
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public List<Order> getOrders(){
		return orderRepo.findAll();
	}
	
	public List<Order> getOrderByUser(HttpServletRequest req){
		
		//extract user id from spring security or jwt
			
		String jwt = req.getHeader("Authorization").split(" ")[1];
		String username = jwtUtil.extractUsername(jwt);
		
		User user = userRepo.findByUsername(username).get();
		
		int userId = user.getId();
		
		List<Order> orders = orderRepo.getOrdersByUserId(userId);
		for(Order order: orders) {
			order.setUser(user);
		}
		
		return orders;
}
	
	public Order createOrder(Order order, HttpServletRequest req){
			
			//extract user id from spring security or jwt
				
			String jwt = req.getHeader("Authorization").split(" ")[1];
			String username = jwtUtil.extractUsername(jwt);
			
			User user = userRepo.findByUsername(username).get();
			
		
			order.setId(null);
			
			
			//user added
			order.setUser(user);
			
			//produc
			
			Order added = orderRepo.save(order);
			
			
			return added;
			
	}
	
	public List<Order> getOrdersByStatus(String status) {
		
		return orderRepo.getOrdersFromStatus(status);
		
		
	}

}
