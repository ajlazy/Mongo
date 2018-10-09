package com.capgemini.productapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		throw new ProductNotFoundException("Product does not exists");
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public Product findProductByName(String name) throws ProductNotFoundException{
		return productRepository.findAllByProductName(name);
		
	}

	@Override
	public List<Product> findProductByCategory(String category) throws ProductNotFoundException
	{
		return productRepository.findAllByproductCategory(category);
	}
	
	public Product findProductByCategoryAndPrice(String productcategory,int priceUpperLimit,int priceLowerLimit) throws ProductNotFoundException
	{
		return productRepository.findProductByCategoryAndPrice(productcategory, priceUpperLimit, priceLowerLimit);
		
	}
	}


	
