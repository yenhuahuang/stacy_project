package com.example.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.MyKey;

@Entity
public class Orderdetail {

	@EmbeddedId
	private MyKey myKey;

	@ManyToOne
	@JoinColumn(name = "findorder")
	private Order order;
	private int count;
	private int price;
    private int stotal = count*price;
	public MyKey getMyKey() {
		return myKey;
	}

	public void setMyKey(MyKey myKey) {
		this.myKey = myKey;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getStotal() {
		return stotal;
	}

	public void setStotal(int stotal) {
		this.stotal = stotal;
	}

	public boolean equals(Orderdetail od) {
		if (this.myKey.getProduct().getId().equals(od.myKey.getProduct().getId())&&this.getOrder().getId().equals(od.getOrder().getId())) {
			return true;
		} else {
			return false;
		}
	}
}
