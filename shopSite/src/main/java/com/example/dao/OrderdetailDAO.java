package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.MyKey;
import com.example.entity.Order;
import com.example.entity.Orderdetail;

public interface OrderdetailDAO extends CrudRepository<Orderdetail, MyKey> {
	public Iterable<Orderdetail> findByOrder(Order order);

}
