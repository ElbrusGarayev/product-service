package com.productservice.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBaseException extends RuntimeException{

    String code;
    String message;
    LocalDateTime dateTime;

    public ProductBaseException(String code, String message){
        this.code = code;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}
