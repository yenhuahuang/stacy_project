package com.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productdetail")
public class Productdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "name")
	public Product product;
	@Column(name = "inventory")
	private int inventory;

	@NotNull(message = "請輸入有效日期")
	@Column(name = "expire")
	private Date expire;
	private Date indate;
	private Date reach;
	private int isreach = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate() {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		this.indate = sqlDate;
	}

	public Date getReach() {
		return reach;
	}

	public void setReach() {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		this.reach = sqlDate;
	}

	public int getIsreach() {
		return isreach;
	}

	public void setIsreach(int isreach) {
		this.isreach = isreach;
	}

}
