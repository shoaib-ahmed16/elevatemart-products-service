package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTokenIssuer extends RuntimeException{
    public InvalidTokenIssuer(String message){
        super(message);
    }
}
