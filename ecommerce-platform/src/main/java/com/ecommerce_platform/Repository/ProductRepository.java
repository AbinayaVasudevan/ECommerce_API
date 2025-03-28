package com.ecommerce_platform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce_platform.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


	List<Product> findByName(String name);

}
