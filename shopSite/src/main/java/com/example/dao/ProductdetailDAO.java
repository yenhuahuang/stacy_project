package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Product;
import com.example.entity.Productdetail;

public interface ProductdetailDAO extends CrudRepository<Productdetail, Long>{
	public Iterable<Productdetail> findByProduct(Product product);
		
}
