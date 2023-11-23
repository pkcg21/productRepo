package com.cg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Product;
import com.cg.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepo;
	
	public Optional<Product> getProduct(int id)
	{
		Optional<Product>p = productRepo.findById(id);
		return p;
	}
	
	
	public List<Product> getProductsFromDatabase(){
		return productRepo.findAll();
	}
	public List<Product> listAll() {

		List<Product> products= productRepo.findAll();
		System.out.println(products);
		return products;
	}
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}	
	public Map<String,Boolean> deleteProduct(Integer productId){

		productRepo.deleteById(productId);
		Map<String,Boolean> response=new HashMap<>();
		response.put("product has been deleted", Boolean.TRUE);
		return response;
	}
	public String updateProduct(Integer productId, Product newProduct) {
		Optional<Product> existingProduct = productRepo.findById(productId);
		if(existingProduct.isPresent()) {
			Product foundProduct = existingProduct.get();
			foundProduct.setName(newProduct.getName());
			foundProduct.setPrice(newProduct.getPrice());
			productRepo.save(foundProduct);
 
			return "product Updated";
		}
		return "product Not Updated";

	}

}
