package com.capgemini.productapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;


public interface ProductRepository extends MongoRepository<Product, Integer>{
	
	
	Product findAllByProductName(String name) throws ProductNotFoundException;
	public List<Product> findAllByproductCategory(String category) throws ProductNotFoundException;
	//Product findProductByProductCategoryAndProductPrice

	@Query(value="{ 'productCategory' : ?0 , 'age':{$gt: ?1, $lt:?2} 	}")
	  Product findProductByCategoryAndPrice(String productcategory,int priceUpperLimit,int priceLowerLimit) throws ProductNotFoundException;
	
	
}