package com.productservice.mapper;

import com.productservice.dto.ProductDTO;
import com.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE  = Mappers.getMapper(ProductMapper.class);

    ProductDTO modelToDTO(Product product);
}
