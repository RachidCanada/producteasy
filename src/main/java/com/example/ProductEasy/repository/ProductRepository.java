package com.example.ProductEasy.repository;

import com.example.ProductEasy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
