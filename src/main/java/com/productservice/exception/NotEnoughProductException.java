package com.productservice.exception;

import com.productservice.enums.ExceptionEnum;

public class NotEnoughProductException extends ProductBaseException{

    public NotEnoughProductException() {
        super(ExceptionEnum.NOT_ENOUGH_PRODUCT.getCode(), ExceptionEnum.NOT_ENOUGH_PRODUCT.name().toLowerCase());
    }
}
