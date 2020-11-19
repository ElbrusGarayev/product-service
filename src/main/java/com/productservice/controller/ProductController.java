package com.productservice.controller;

import com.productservice.dto.PageAndSizeDTO;
import com.productservice.dto.ProductDTO;
import com.productservice.dto.SearchProductDTO;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/searching")
    public ResponseEntity<List<ProductDTO>> searchProduct(SearchProductDTO searchProductDTO){
        return ResponseEntity.ok(productService.search(searchProductDTO));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable String id){
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable String id){
        productService.delete(id);
    }
}
