package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AttributeUnknownServerErrorException extends RuntimeException{
    public AttributeUnknownServerErrorException(String message){
        super(message);
    }
}
