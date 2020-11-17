package com.productservice.init;

import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreator {

    final ProductService productService;

    @Bean
    public CommandLineRunner init() {
        if (productService.findAll().size() == 0)
            return args -> {
                productService.save(new Product("Pant", "Blue pant", 50, "Gucci",
                        'L', 10, "Type", "Blue", 'M'));
                productService.save(new Product("Sweatshirt", "Blue sweatshirt", 50, "Defacto",
                        'S', 20, "Type", "Blue", 'M'));
            };
        else
            return null;
    }
}
