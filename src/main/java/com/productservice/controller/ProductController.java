package com.productservice.controller;

import com.productservice.model.Product;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service/api")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping("/products")
    ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/product")
    ResponseEntity<Product> getProduct(@RequestParam String id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PutMapping("/product-saving")
    ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/product-deleting")
    void deleteProduct(@RequestParam String id){
        productService.delete(id);
    }
}
