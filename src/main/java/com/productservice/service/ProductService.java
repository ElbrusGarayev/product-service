package com.productservice.service;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO save(ProductDTO product);

    ProductDTO update(String id, ProductDTO product);

    List<ProductDTO> findAll(PageAndSizeDTO pageAndSizeDTO);

    ProductDTO findById(String id);

    void delete(String id);
}
