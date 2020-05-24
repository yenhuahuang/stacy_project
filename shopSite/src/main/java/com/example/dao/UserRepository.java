package com.example.dao;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.entity.Product;

import antlr.collections.List;


public interface UserRepository extends CrudRepository<Product, String> {
	 
  public Iterable<Product> findByNameLike(String name);
}
