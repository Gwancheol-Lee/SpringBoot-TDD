package com.example.productorderservice.product.adapter;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productorderservice.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}