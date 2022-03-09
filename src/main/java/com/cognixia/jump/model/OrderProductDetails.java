package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Order_Prducts many to many relationships
@Entity
@Table(name = "orders_products")
public class OrderProductDetails implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	
	@ManyToOne(cascade = CascadeType.MERGE) //create foreign key
	@JoinColumn(name="order_id", referencedColumnName = "id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName = "id")
	private Product product;
	
	@Column(nullable = false)
	private int qty;

	public OrderProductDetails() {
		
	}

	public OrderProductDetails(Integer id, Order order, Product product, int qty) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.qty = qty;
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "OrderProductDetails [id=" + id + ", order=" + order + ", product=" + product + ", qty=" + qty + "]";
	}
	
	
	
}
