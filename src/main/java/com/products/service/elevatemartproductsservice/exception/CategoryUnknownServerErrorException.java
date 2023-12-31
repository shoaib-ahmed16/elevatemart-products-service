package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryUnknownServerErrorException extends RuntimeException{
    public CategoryUnknownServerErrorException(String message){
        super(message);
    }
}
