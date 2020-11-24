package com.productservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageAndSizeDTO {

    @NotNull(message = "page_cannot_be_empty")
    int page;
    @NotNull(message = "size_cannot_be_empty")
    int size;
}
