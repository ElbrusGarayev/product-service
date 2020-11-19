package com.productservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productservice.enums.GenderEnum;
import com.productservice.enums.SizeEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductDTO {

    String id;
    @NotBlank(message = "Name is required!")
    String name;
    @NotBlank(message = "Description is required!")
    String description;
    @NotNull
    @Positive(message = "Price cannot be Zero or negative")
    int price;
    @NotBlank(message = "Brand is required!")
    String brand;
    @NotNull
    SizeEnum size;
    @NotNull
    @Positive(message = "Stock count cannot be Zero or negative")
    int stockCount;
    @NotBlank(message = "Type is required!")
    String type;
    @NotBlank(message = "Color is required!")
    String color;
    @NotNull
    GenderEnum gender;
    @JsonIgnore
    LocalDateTime createdDate;
    @JsonIgnore
    LocalDateTime lastModifiedDate;
}
