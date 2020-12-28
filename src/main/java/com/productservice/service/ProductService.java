package com.productservice.service;

import com.productservice.dto.*;

import java.util.List;

public interface ProductService {

    ProductDTO save(ProductDTO product);

    ProductDTO update(String id, ProductDTO product);

    List<ProductDTO> findAll(PageAndSizeDTO pageAndSizeDTO);

    ProductDTO findById(String id);

    void delete(String id);

    List<ProductDTO> search(SearchProductDTO productDTO);

    OrderedProductDTO purchase(OrderDTO orderDTO);
}
