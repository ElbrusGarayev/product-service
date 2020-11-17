package com.productservice.service.impl;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
import com.productservice.mapper.ProductMapper;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final ProductMapper productMapper;


    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.dtoToModel(productDTO);
        return productMapper.modelToDto(productRepository.save(product));
    }

    @Override
    public ProductDTO update(String id, ProductDTO productDTO) {
        Product product = productMapper.dtoToModel(productDTO);
        return productMapper.modelToDto(productRepository.save(product));
    }

    @Override
    public List<ProductDTO> findAll(PageAndSizeDTO pageAndSizeDTO) {
        Pageable pageable = PageRequest.of(pageAndSizeDTO.getPage(), pageAndSizeDTO.getSize());
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        return products.stream().map(productMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(null);
        return productMapper.modelToDto(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
