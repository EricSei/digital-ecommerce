package com.cognixia.jump.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String specification;

	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private double price;
	
	@Column(nullable = false)
	private int qty;
	
	
	//1 prodcut to many product_order
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderProductDetails> orderProducts;
	
	public Product() {
		
	}

	public Product(Integer id, String name, String specification, String brand, String category, double price, int qty, List<OrderProductDetails> orderProducts) {
		super();
		this.id = id;
		this.name = name;
		this.specification = specification;
		this.brand = brand;
		this.category = category;
		this.qty = qty;
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", specification=" + specification + ", brand=" + brand
				+ ", category=" + category + ", price=" + price + ", qty=" + qty + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	

}
