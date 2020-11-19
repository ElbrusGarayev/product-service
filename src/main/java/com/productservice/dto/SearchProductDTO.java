package com.productservice.dto;

import com.productservice.enums.GenderEnum;
import com.productservice.enums.SizeEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchProductDTO extends PageAndSizeDTO{

    String name;
    String description;
    int price;
    String brand;
    SizeEnum productSize;
    int stockCount;
    String type;
    String color;
    GenderEnum gender;
}
