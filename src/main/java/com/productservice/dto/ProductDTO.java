package com.productservice.dto;

import com.productservice.enums.GenderEnum;
import com.productservice.enums.SizeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductDTO {

    String id;
    String name;
    String description;
    int price;
    String brand;
    SizeEnum size;
    int stockCount;
    String type;
    String color;
    GenderEnum gender;
}
