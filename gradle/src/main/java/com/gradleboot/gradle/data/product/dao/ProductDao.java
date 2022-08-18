package com.gradleboot.gradle.data.product.dao;

import com.gradleboot.gradle.data.product.entity.Product;

public interface ProductDao {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProdict(Long number) throws Exception;
}
