package com.productservice.enums;

import lombok.Getter;

@Getter
public enum ProductUtil {

    NAME("name"),
    DESCRIPTION("description"),
    PRICE ("price"),
    BRAND ("brand"),
    SIZE ("size"),
    STOCK_COUNT ("stock_count"),
    TYPE ("type"),
    COLOR ("color"),
    GENDER ("gender");

    private final String label;

    ProductUtil(String label) {
        this.label = label;
    }
}
