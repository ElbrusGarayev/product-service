package com.productservice.exception;

import com.productservice.enums.ExceptionEnum;

public class ProductNotFoundException extends ProductBaseException {

    public ProductNotFoundException() {
        super(ExceptionEnum.PRODUCT_NOT_FOUND.getCode(), ExceptionEnum.PRODUCT_NOT_FOUND.name().toLowerCase());
    }
}
