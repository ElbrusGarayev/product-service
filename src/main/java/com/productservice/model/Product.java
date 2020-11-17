package com.productservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    String id;
    String name;
    String description;
    int price;
    String brand;
    char size;
    @Field(name = "stock_count")
    int stockCount;
    String type;
    String color;
    char gender;

    public Product(String name, String description, int price, String brand, char size, int stockCount, String type, String color, char gender) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.size = size;
        this.stockCount = stockCount;
        this.type = type;
        this.color = color;
        this.gender = gender;
    }
}
