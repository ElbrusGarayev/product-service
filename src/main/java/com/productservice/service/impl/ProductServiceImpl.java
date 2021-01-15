package com.productservice.service.impl;

import com.productservice.dto.*;
import com.productservice.enums.ExceptionEnum;
import com.productservice.exception.NotEnoughProductException;
import com.productservice.exception.ProductAlreadyExistsException;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.mapper.ProductMapper;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.productservice.enums.ProductUtil.*;

@Log4j2
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final ProductMapper productMapper;
    final MongoTemplate mongoTemplate;


    @Override
    @CacheEvict(value = "all_products", allEntries = true)
    public ProductDTO save(ProductDTO productDTO) {
        productRepository.findByName(productDTO.getName()).ifPresent(t -> {
            throw new ProductAlreadyExistsException(ExceptionEnum.PRODUCT_ALREADY_EXISTS);
        });
        Product product = productMapper.dtoToModel(productDTO);
        return productMapper.modelToDto(productRepository.save(product));
    }

    @Override
    @CachePut(value = "product_by_id", key = "#productDTO.id")
    public ProductDTO update(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Product newProduct = productMapper.dtoToModel(productDTO);
        newProduct.setId(product.getId());
        newProduct.setCreatedDate(product.getCreatedDate());
        return productMapper.modelToDto(productRepository.save(newProduct));
    }

    @Override
    @Cacheable(value = "all_products")
    public List<ProductDTO> findAll(PageAndSizeDTO pageAndSizeDTO) {
        Pageable pageable = PageRequest.of(pageAndSizeDTO.getPage(), pageAndSizeDTO.getSize());
        Page<Product> page = productRepository.findAll(pageable);
        List<Product> products = page.getContent();
        return products.stream().map(productMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "product_by_id", key = "#id")
    public ProductDTO findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return productMapper.modelToDto(product);
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> search(SearchProductDTO productDTO) {
        List<Criteria> criteria = new ArrayList<>();
        String insensitive = "i";
        if (StringUtils.isNotBlank(productDTO.getName()))
            criteria.add(Criteria.where(NAME.getLabel()).regex(productDTO.getName(), insensitive));
        if (StringUtils.isNotBlank(productDTO.getDescription()))
            criteria.add(Criteria.where(DESCRIPTION.getLabel()).regex(productDTO.getDescription(), insensitive));
        if (productDTO.getPrice() != 0)
            criteria.add(Criteria.where(PRICE.getLabel()).is(productDTO.getPrice()));
        if (StringUtils.isNotBlank(productDTO.getBrand()))
            criteria.add(Criteria.where(BRAND.getLabel()).regex(productDTO.getBrand(), insensitive));
        if (productDTO.getProductSize() != null)
            criteria.add(Criteria.where(SIZE.getLabel()).is(productDTO.getProductSize()));
        if (productDTO.getStockCount() != 0)
            criteria.add(Criteria.where(STOCK_COUNT.getLabel()).is(productDTO.getStockCount()));
        if (StringUtils.isNotBlank(productDTO.getType()))
            criteria.add(Criteria.where(TYPE.getLabel()).regex(productDTO.getType(), insensitive));
        if (StringUtils.isNotBlank(productDTO.getColor()))
            criteria.add(Criteria.where(COLOR.getLabel()).regex(productDTO.getColor(), insensitive));
        if (productDTO.getGender() != null)
            criteria.add(Criteria.where(GENDER.getLabel()).is(productDTO.getGender()));

        Criteria multiCriteria = new Criteria().andOperator(criteria.toArray(new Criteria[0]));

        Pageable pageable = PageRequest.of(productDTO.getPage(), productDTO.getSize());
        Query query = new Query().with(pageable);
        query.addCriteria(multiCriteria);
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products.stream().map(productMapper::modelToDto).collect(Collectors.toList());
    }

    @Override
    public OrderedProductDTO purchase(OrderDTO orderDTO) {
        Product product = productRepository.findById(orderDTO.getProductId()).orElseThrow(ProductNotFoundException::new);
        if (product.getStockCount() >= orderDTO.getProductCount()){
            product.setStockCount(product.getStockCount() - orderDTO.getProductCount());
        }else{
            throw new NotEnoughProductException();
        }
        ProductDTO productDTO = productMapper.modelToDto(productRepository.save(product));
        return productMapper.toOrderedProductDTO(productDTO);
    }

}
