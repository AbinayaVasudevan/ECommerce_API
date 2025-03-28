package com.ecommerce_platform.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_platform.Entity.Product;
import com.ecommerce_platform.Service.ProductService;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}
	
	@GetMapping("/getAllProduct")
	public List<Product> getAllProduct(){
		return service.getAllProduct();
	}
	
	@GetMapping("/getProductByName/{name}")
	public List<Product> getProductByName(@PathVariable String name){
		return service.getProductByName(name);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/updateProduct/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
		return service.updateProduct(product,id);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable Long id) {
		Optional<Product> prd=service.deleteById(id);
		return ResponseEntity.ok(prd);
	}
}
