package com.springboot.guidesources.jpa.service.impl;

import com.springboot.guidesources.jpa.data.dto.ProductDto;
import com.springboot.guidesources.jpa.data.dto.ProductResponseDto;
import com.springboot.guidesources.jpa.service.ProductService;

public class ProductServiceImpl implements ProductService {

    @Override
    public ProductResponseDto getProduct(Long number) {
        return null;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) {
        return null;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

    }
}
