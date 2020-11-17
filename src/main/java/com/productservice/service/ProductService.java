package com.productservice.service;

import com.productservice.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Product findById(String id);

    void delete(String id);
}
