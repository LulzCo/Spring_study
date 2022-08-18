package com.gradleboot.gradle.data.product.repository;

import com.gradleboot.gradle.data.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
