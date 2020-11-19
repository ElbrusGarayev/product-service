package com.productservice.service.impl;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
import com.productservice.dto.SearchProductDTO;
import com.productservice.mapper.ProductMapper;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.productservice.util.ProductUtil.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final ProductMapper productMapper;
    final MongoTemplate mongoTemplate;


    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.dtoToModel(productDTO);
        return productMapper.modelToDto(productRepository.save(product));
    }

    @Override
    public ProductDTO update(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(null);
        Product newProduct = productMapper.dtoToModel(productDTO);
        newProduct.setId(product.getId());
        newProduct.setCreatedDate(product.getCreatedDate());
        return productMapper.modelToDto(productRepository.save(newProduct));
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

    @Override
    public List<ProductDTO> search(SearchProductDTO productDTO) {
        Criteria criteria = null;
        String insensitive = "i";
        if (StringUtils.isNotBlank(productDTO.getName()))
            criteria = Criteria.where(NAME.getLabel()).regex(productDTO.getName(), insensitive);
        if (StringUtils.isNotBlank(productDTO.getDescription()))
            criteria = criteria != null ? criteria.and(DESCRIPTION.getLabel()).regex(productDTO.getDescription(), insensitive) : Criteria.where(DESCRIPTION.getLabel()).regex(productDTO.getDescription(), insensitive);
        if (productDTO.getPrice() != 0)
            criteria = criteria != null ? criteria.and(PRICE.getLabel()).is(productDTO.getPrice()) : Criteria.where(PRICE.getLabel()).is(productDTO.getPrice());
        if (StringUtils.isNotBlank(productDTO.getBrand()))
            criteria = criteria != null ? criteria.and(BRAND.getLabel()).regex(productDTO.getBrand(), insensitive) : Criteria.where(BRAND.getLabel()).regex(productDTO.getBrand(), insensitive);
        if (productDTO.getProductSize() != null)
            criteria = criteria != null ? criteria.and(SIZE.getLabel()).is(productDTO.getProductSize()) : Criteria.where(SIZE.getLabel()).is(productDTO.getProductSize());
        if (productDTO.getStockCount() != 0)
            criteria = criteria != null ? criteria.and(STOCK_COUNT.getLabel()).is(productDTO.getStockCount()) : Criteria.where(STOCK_COUNT.getLabel()).is(productDTO.getStockCount());
        if (StringUtils.isNotBlank(productDTO.getType()))
            criteria = criteria != null ? criteria.and(TYPE.getLabel()).regex(productDTO.getType(), insensitive) : Criteria.where(TYPE.getLabel()).regex(productDTO.getType(), insensitive);
        if (StringUtils.isNotBlank(productDTO.getColor()))
            criteria = criteria != null ? criteria.and(COLOR.getLabel()).regex(productDTO.getColor(), insensitive) : Criteria.where(COLOR.getLabel()).regex(productDTO.getColor(), insensitive);
        if (productDTO.getGender() != null)
            criteria = criteria != null ? criteria.and(GENDER.getLabel()).is(productDTO.getGender()) : Criteria.where(GENDER.getLabel()).is(productDTO.getGender());
        Pageable pageable = PageRequest.of(productDTO.getPage(), productDTO.getSize());
        Query query = new Query().with(pageable);
        query = criteria != null ? query.addCriteria(criteria).with(pageable) : query;
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products.stream().map(productMapper::modelToDto).collect(Collectors.toList());
    }

}
