package com.productservice.controller;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
import com.productservice.dto.RestResponseDTO;
import com.productservice.dto.SearchProductDTO;
import com.productservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;
    final String MESSAGE = "Success";

    @GetMapping("/all")
    @ApiOperation(value = "Get all products", response = ProductDTO.class, responseContainer = "List")
    public ResponseEntity<RestResponseDTO<List<ProductDTO>>> getProducts(@Valid PageAndSizeDTO pageAndSizeDTO){
        return ResponseEntity.ok(new RestResponseDTO<>(productService.findAll(pageAndSizeDTO), MESSAGE));
    }

    @GetMapping("/product/{id}")
    @ApiOperation(value = "Product by id", response = ProductDTO.class)
    public ResponseEntity<RestResponseDTO<ProductDTO>> getProductById(@PathVariable String id){
        return ResponseEntity.ok(new RestResponseDTO<>(productService.findById(id), MESSAGE));
    }

    @GetMapping("/searching")
    @ApiOperation(value = "Search products by any field except id", response = ProductDTO.class, responseContainer = "List")
    public ResponseEntity<RestResponseDTO<List<ProductDTO>>> searchProduct(SearchProductDTO searchProductDTO){
        return ResponseEntity.ok(new RestResponseDTO<>(productService.search(searchProductDTO), MESSAGE));
    }

    @PostMapping("/saving")
    @ApiOperation(value = "Save new product", response = ProductDTO.class)
    public ResponseEntity<RestResponseDTO<ProductDTO>> save(@Valid @RequestBody ProductDTO product){
        return ResponseEntity.ok(new RestResponseDTO<>(productService.save(product), MESSAGE));
    }

    @PutMapping("/updating/{id}")
    @ApiOperation(value = "Update existing product", response = ProductDTO.class)
    public ResponseEntity<RestResponseDTO<ProductDTO>> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable String id){
        return ResponseEntity.ok(new RestResponseDTO<>(productService.update(id, productDTO), MESSAGE));
    }

    @DeleteMapping("/deleting/{id}")
    @ApiOperation(value = "Delete product by id")
    public void deleteProduct(@PathVariable String id){
        productService.delete(id);
    }
}
