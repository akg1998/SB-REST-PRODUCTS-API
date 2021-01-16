package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
