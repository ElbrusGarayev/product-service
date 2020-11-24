package com.productservice.exception;

import com.productservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductNotFoundException extends ProductBaseException {

    public ProductNotFoundException(String code, String message) {
        super(code, message);
    }

    public ProductNotFoundException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.name().toLowerCase());
    }
}
