package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Member;
import com.example.entity.Order;


public interface OrderDAO extends CrudRepository<Order, Long>{
	public Iterable<Order> findByMember(Member member);
	
}
