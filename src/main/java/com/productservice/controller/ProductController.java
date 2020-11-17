package com.productservice.controller;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
import com.productservice.model.Product;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping("/all")
    ResponseEntity<List<ProductDTO>> getProducts(PageAndSizeDTO pageAndSizeDTO){
        return ResponseEntity.ok(productService.findAll(pageAndSizeDTO));
    }

    @GetMapping("{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("{id}")
    ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product, @PathVariable String id){
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/product-deleting")
    void deleteProduct(@RequestParam String id){
        productService.delete(id);
    }
}
