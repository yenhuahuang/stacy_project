package com.example;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.entity.Order;
import com.example.entity.Product;

@Embeddable
public class MyKey implements Serializable {

	@Column(name = "orderid", nullable = false)
	private long orderid;

	@ManyToOne
	@JoinColumn(name = "productid", nullable = false)
	private Product product;

	/** getters and setters **/

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}