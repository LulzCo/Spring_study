package com.springboot.guidesources.jpa.service;

import com.springboot.guidesources.jpa.data.dto.ProductDto;
import com.springboot.guidesources.jpa.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;
}
