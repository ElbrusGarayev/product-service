package com.productservice.service;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
import com.productservice.model.Product;

import java.util.List;

public interface ProductService {

    ProductDTO save(ProductDTO product);

    ProductDTO update(ProductDTO product);

    List<ProductDTO> findAll(PageAndSizeDTO pageAndSizeDTO);

    ProductDTO findById(String id);

    void delete(String id);
}
