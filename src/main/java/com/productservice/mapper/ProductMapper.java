package com.productservice.mapper;

import com.productservice.dto.ProductDTO;
import com.productservice.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO modelToDto(Product product);

    Product dtoToModel(ProductDTO productDTO);
}
