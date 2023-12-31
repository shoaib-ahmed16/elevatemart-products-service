package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AttributeNotFoundException extends RuntimeException{

    public AttributeNotFoundException(String message){
        super(message);
    }
}
