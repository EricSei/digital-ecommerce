package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

//Order 1 -> OrderProduct many
@Entity
@Table(name = "orders")
public class Order  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	 @Column(name="date_added", updatable = false)
	 @CreationTimestamp
	 private LocalDateTime dateAdded;
	
	@Column
	private String status; //status: (placed, delivering, delivered)
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderProductDetails> orderProducts;
	
	public Order() {
		
	}
	

	public Order(Integer id, LocalDateTime dateAdded, String status, User user,
			List<OrderProductDetails> orderProducts) {
		super();
		this.id = id;
		this.dateAdded = dateAdded;
		this.status = status;
		this.user = user;
		this.orderProducts = orderProducts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderProductDetails> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductDetails> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", dateAdded=" + dateAdded + ", status=" + status + ", user=" + user
				+ ", orderProducts=" + orderProducts + "]";
	}
	
}
