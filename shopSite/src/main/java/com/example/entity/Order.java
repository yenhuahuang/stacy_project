package com.example.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orderlist")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date orderdate;
	@ManyToOne
	@JoinColumn(name = "memid")
	private Member member;
	private int total = 0;
	@OneToMany(mappedBy = "order")
	private List<Orderdetail> orderdetails;
	private String situation = "未出貨";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(int i) {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		this.orderdate = sqlDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal() {
		int temp = 0;
		try {
			for (Orderdetail od : orderdetails) {
				temp += od.getStotal();
			}
			this.total = temp;
		} catch (Exception ex) {
			this.total = temp;
		}
	}

	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
	

}
