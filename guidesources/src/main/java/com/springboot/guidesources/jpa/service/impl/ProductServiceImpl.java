package com.springboot.guidesources.jpa.service.impl;

import com.springboot.guidesources.jpa.data.dao.ProductDao;
import com.springboot.guidesources.jpa.data.dao.impl.ProductDaoImpl;
import com.springboot.guidesources.jpa.data.dto.ProductDto;
import com.springboot.guidesources.jpa.data.dto.ProductResponseDto;
import com.springboot.guidesources.jpa.data.entity.Product;
import com.springboot.guidesources.jpa.service.ProductService;
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
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        Product product = new Product();
        product.setUpdateAt(LocalDateTime.now());
        product.setStock(productDto.getStock());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productDao.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product changedProduct = productDao.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setStock(changedProduct.getStock());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setNumber(changedProduct.getNumber());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        productDao.deleteProduct(number);

    }
}
