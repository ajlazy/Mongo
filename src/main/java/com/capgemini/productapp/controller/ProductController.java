package com.capgemini.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);

		return responseEntity;
	}

	/*
	 * @PostMapping("/product") public @ResponseBody Product addProduct(@RequestBody
	 * Product product) { product = productService.addProduct(product);
	 * 
	 * 
	 * return product; }
	 */

	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			productService.findProductById(product.getProductId());
			return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/products/delete/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		try {
			Product productFromDb = productService.findProductById(productId);
			if (productFromDb != null) {
				productService.deleteProduct(productFromDb);
				return new ResponseEntity<Product>(HttpStatus.OK);
			}
		} catch (ProductNotFoundException exception) {
			// logged the exception
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/products/findByName/{productName}")
	public ResponseEntity<List<Product>> findProductByName(@PathVariable String productName) {
		try {
			Product productFromDb = productService.findProductByName(productName);
			if (productFromDb != null)
				return new ResponseEntity<List<Product>>(HttpStatus.OK);
		} catch (ProductNotFoundException exception) {
			// logger
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/products/findByCategory/{productCategory}")
	public ResponseEntity<List<Product>> findProductByproductCategory(@PathVariable String productCategory) throws ProductNotFoundException {
		ResponseEntity<List<Product>> responseEntity = new ResponseEntity<List<Product>>(productService.findProductByCategory(productCategory), HttpStatus.OK);
				return responseEntity;
	}

	@GetMapping("/products/findByCategoryAndPrice")
	public ResponseEntity<List<Product>> findProductByproductNameandPrice(@RequestParam String productCategory,
			@RequestParam int priceUpperLimit, int priceLowerlimit) {
		try {
		Product productFromDb = productService.findProductByCategoryAndPrice(productCategory, priceUpperLimit,
				priceLowerlimit);
		
		if (productFromDb != null)
			return new ResponseEntity<List<Product>>(HttpStatus.OK);
	} catch (ProductNotFoundException exception) {
		// logger
	}
	return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}

}
