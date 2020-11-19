package com.productservice.listener;

import com.productservice.model.Product;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductListener extends AbstractMongoEventListener<Product> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Product> event) {
        Product product = event.getSource();
        if (product.getCreatedDate() == null) {
            product.setCreatedDate(LocalDateTime.now());
        } else {
            product.setLastModifiedDate(LocalDateTime.now());
        }
    }
}
