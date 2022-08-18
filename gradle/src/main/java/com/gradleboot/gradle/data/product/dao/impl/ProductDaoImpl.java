package com.gradleboot.gradle.data.product.dao.impl;

import com.gradleboot.gradle.data.product.dao.ProductDao;
import com.gradleboot.gradle.data.product.entity.Product;
import com.gradleboot.gradle.data.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoImpl implements ProductDao {

    private ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product saveProduct = productRepository.save(product);

        return saveProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        return null;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        return null;
    }

    @Override
    public void deleteProdict(Long number) throws Exception {

    }
}
