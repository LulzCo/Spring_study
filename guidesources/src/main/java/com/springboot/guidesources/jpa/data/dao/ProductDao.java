package com.springboot.guidesources.jpa.data.dao;

import com.springboot.guidesources.jpa.data.entity.Product;

public interface ProductDao {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProduct(Long number, String name);

    void deleteProduct(Long number) throws Exception;
}
