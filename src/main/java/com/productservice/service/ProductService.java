package com.productservice.service;

import com.productservice.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();
}
