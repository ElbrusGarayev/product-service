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

import java.util.ArrayList;
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

}
