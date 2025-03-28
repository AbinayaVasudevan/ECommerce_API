package com.ecommerce_platform.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_platform.Entity.Product;
import com.ecommerce_platform.Repository.ProductRepository;
@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	public List<Product> getAllProduct() {
		return repo.findAll();
	}

	public List<Product> getProductByName(String name) {
		return repo.findByName(name);
	}

	public Product updateProduct(Product product, Long id) {
		product.setId(id);
		return repo.save(product);
		
	}

	public Optional<Product> deleteById(Long id) {
		Optional<Product> prd=repo.findById(id);
		prd.ifPresent(pro -> repo.deleteById(id));
		return prd;
	}

}
