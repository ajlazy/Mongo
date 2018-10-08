package com.capgemini.productapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capgemini.productapp.entity.Product;

public interface ProductRepository extends MongoRepository<Product, Integer>{
	
	
	Product findProductByName(String name);
	Product findProductByProductCategory(String category);
	//product findProductByProductCategoryAndProductPrice

}