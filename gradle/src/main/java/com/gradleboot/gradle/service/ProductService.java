package com.gradleboot.gradle.service;

import com.gradleboot.gradle.data.product.dto.ProductDto;
import com.gradleboot.gradle.data.product.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
