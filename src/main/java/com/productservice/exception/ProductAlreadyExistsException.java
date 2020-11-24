package com.productservice.exception;

import com.productservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAlreadyExistsException extends ProductBaseException{

    public ProductAlreadyExistsException(String code, String message) {
        super(code, message);
    }

    public ProductAlreadyExistsException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.name().toLowerCase());
    }
}
