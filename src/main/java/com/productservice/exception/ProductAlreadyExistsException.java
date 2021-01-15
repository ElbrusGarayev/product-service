package com.productservice.exception;

import com.productservice.enums.ExceptionEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

public class ProductAlreadyExistsException extends ProductBaseException{

    public ProductAlreadyExistsException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.name().toLowerCase());
    }
}
