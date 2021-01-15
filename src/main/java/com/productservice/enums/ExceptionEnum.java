package com.productservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ExceptionEnum {

    PRODUCT_NOT_FOUND("0001"),
    PRODUCT_ALREADY_EXISTS("0002"),
    PRODUCT_VALIDATION_ERROR("0003"),
    NOT_ENOUGH_PRODUCT("0004"),
    SERVER_ERROR("1000");

    final String code;
}
