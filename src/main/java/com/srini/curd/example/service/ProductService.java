package com.srini.curd.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srini.curd.example.entity.Product;
import com.srini.curd.example.exception.UserDataAccessException;
import com.srini.curd.example.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public List<Product> saveProducts(List<Product> products){
		
		return repository.saveAll(products);
		
	}
	
	public Product getProductsById(int id){
		return repository.findById(id).orElse(null);
	}
	public Product getProductsByName(String name) throws UserDataAccessException{
		
		if(name==null || name.equals("")) {
			throw new UserDataAccessException("Product name can't be null or empty - "+name);
		}
		
		Product product = repository.findByName(name);
		if(product != null)
				return product;
		
		throw new UserDataAccessException("Provide valid product name - "+name);
		 
	}
	public String deleteProduct(int id) {
	repository.deleteById(id);
	return "Product removed ||"+id;
	}
	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		 existingProduct.setName(product.getName());
		 existingProduct.setPrice(product.getPrice());
		 existingProduct.setQuantity(product.getQuantity());
		 return repository.save(existingProduct);
	}
	
	public List<Product> getProducts(){
		return repository.findAll();
	}

}
