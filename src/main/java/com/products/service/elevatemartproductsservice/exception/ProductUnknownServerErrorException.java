package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductUnknownServerErrorException extends RuntimeException{
    public ProductUnknownServerErrorException(String message){
        super(message);
    }
}
