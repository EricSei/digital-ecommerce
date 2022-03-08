package com.cognixia.jump.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello World";
	}
	
	@GetMapping("/admin") 
	public String getAdmin() {
		return "only admin can access this api";
	}
	
	@GetMapping("/book")
	public String getBook() {
		return "Only users and admin can access the books";
	}
	
	@GetMapping("/product")
	public String getProduct() {
		return " GEt a  product";
	}
	
	@PostMapping("/product")
	public String createProduct() {
		return "only a admin a product";
	}
	
	@GetMapping("/student")
	public String getStudent() {
		return "get a student";
	}
	
	@GetMapping("/student/courses")
	public String getCourses() {
		return "get a student courses";
	}
	
	@GetMapping("/student/grades")
	public String getGrades() {
		return "get a student grades";
	}
}
