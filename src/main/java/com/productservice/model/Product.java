package com.productservice.model;

import com.productservice.enums.GenderEnum;
import com.productservice.enums.SizeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    String id;
    String name;
    String description;
    int price;
    String brand;
    SizeEnum size;
    @Field(name = "stock_count")
    int stockCount;
    String type;
    String color;
    GenderEnum gender;
    @Field(name = "created_date")
    LocalDateTime createdDate;
    @Field(name = "last_modified_date")
    LocalDateTime lastModifiedDate;
}
