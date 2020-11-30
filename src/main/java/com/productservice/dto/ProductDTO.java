package com.productservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.productservice.enums.GenderEnum;
import com.productservice.enums.SizeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ApiModel(value = "ProductDTO")
public class ProductDTO implements Serializable {

    @ApiModelProperty(name = "product id", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    String id;
    @ApiModelProperty(name = "product name")
    @NotBlank(message = "name_is_required")
    String name;
    @ApiModelProperty(name = "product description")
    @NotBlank(message = "description_is_required")
    String description;
    @ApiModelProperty(name = "product price")
    @NotNull
    @Positive(message = "price_cannot_be_zero_or_negative")
    int price;
    @ApiModelProperty(name = "product brand")
    @NotBlank(message = "brand_is_required")
    String brand;
    @ApiModelProperty(name = "product size")
    @NotNull
    SizeEnum size;
    @ApiModelProperty(name = "product stock count")
    @NotNull
    @Positive(message = "stock_count_cannot_be_zero_or_negative")
    int stockCount;
    @ApiModelProperty(name = "product type")
    @NotBlank(message = "type_is_required")
    String type;
    @ApiModelProperty(name = "product color")
    @NotBlank(message = "color_is_required")
    String color;
    @ApiModelProperty(name = "product gender")
    @NotNull
    GenderEnum gender;
    @JsonIgnore
    LocalDateTime createdDate;
    @JsonIgnore
    LocalDateTime lastModifiedDate;
}
