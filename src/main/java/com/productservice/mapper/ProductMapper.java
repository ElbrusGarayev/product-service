package com.productservice.mapper;

import com.productservice.dto.ProductDTO;
import com.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO modelToDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Product dtoToModel(ProductDTO productDTO);
}
