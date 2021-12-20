package com.srini.curd.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srini.curd.example.entity.Product;
import com.srini.curd.example.exception.ApiRequestException;
import com.srini.curd.example.exception.UserDataAccessException;
import com.srini.curd.example.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		
		return service.saveProduct(product);
	}
	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products){
		
		return service.saveProducts(products);
	}
	
	@GetMapping("/products")
	public List<Product> findAllProducts(){
		return service.getProducts();
	}
	@GetMapping("/productById/{id}")
	public Product getProductById(@PathVariable int id) {
		Product prod = service.getProductsById(id);
		if(prod == null)
			throw new ApiRequestException("Invalid product Id, please enter valid Id: "+id);
			//throw new IllegalStateException("Invalid Product Id");
		else
			return prod;
	}
	
	@GetMapping("/productByName/{name}")
	public Product getProductByName(@PathVariable String name) throws UserDataAccessException {
		Product product = service.getProductsByName(name);
		if(product == null)
			throw new UserDataAccessException("Invalid Product name, please enter valid product name"+name);
			//return product;
			// TODO Auto-generated catch block
			//throw new UserDataAccessException(e);
			//e.printStackTrace();
			//System.out.println(e);
		return product;
	}
	
	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
	
	
}
