package com.productservice.exception;

import com.productservice.dto.ErrorDTO;
import com.productservice.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class ProductExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleException(ProductNotFoundException ex){
        log.error ("{} error occurred while processing request.", ex.getMessage());
        return ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .dateTime(ex.getDateTime())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ErrorDTO handleException(ProductAlreadyExistsException ex){
        log.error ("{} error occurred while processing request.", ex.getMessage());
        return ErrorDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .dateTime(ex.getDateTime())
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object handleException(MethodArgumentNotValidException ex) {
        log.error ("{} error occurred while processing request.", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        return ErrorDTO
                .builder()
                .code(ExceptionEnum.PRODUCT_VALIDATION_ERROR.getCode())
                .message(fieldError.getDefaultMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorDTO handleException(Throwable ex){
        log.error ("{} error occurred while processing request.", ex.getMessage());
        return ErrorDTO.builder()
                .code(ExceptionEnum.SERVER_ERROR.getCode())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
    }
}
