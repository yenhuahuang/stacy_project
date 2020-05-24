package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Product;
import com.example.entity.ProductCategory;

public interface ProductDAO extends CrudRepository<Product, Long>{
	public Iterable<Product> findByProductCategory(ProductCategory productCategory);
	
	
}
