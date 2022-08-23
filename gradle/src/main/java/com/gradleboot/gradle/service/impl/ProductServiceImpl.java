package com.gradleboot.gradle.service.impl;

import com.gradleboot.gradle.data.product.dao.ProductDao;
import com.gradleboot.gradle.data.product.dto.ProductDto;
import com.gradleboot.gradle.data.product.dto.ProductResponseDto;
import com.gradleboot.gradle.data.product.entity.Product;
import com.gradleboot.gradle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {

        Product product = productDao.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDao.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setStock(savedProduct.getStock());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setNumber(savedProduct.getNumber());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product changeProduct = productDao.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changeProduct.getNumber());
        productResponseDto.setName(changeProduct.getName());
        productResponseDto.setStock(changeProduct.getStock());
        productResponseDto.setPrice(changeProduct.getPrice());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        productDao.deleteProduct(number);

    }
}
