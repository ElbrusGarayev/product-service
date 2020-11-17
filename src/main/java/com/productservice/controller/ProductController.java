package com.productservice.controller;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
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
    public ResponseEntity<List<ProductDTO>> getProducts(PageAndSizeDTO pageAndSizeDTO){
        return ResponseEntity.ok(productService.findAll(pageAndSizeDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @PathVariable String id){
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @DeleteMapping("/product-deleting")
    public void deleteProduct(@RequestParam String id){
        productService.delete(id);
    }
}
