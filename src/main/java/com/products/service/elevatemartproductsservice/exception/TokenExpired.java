package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenExpired extends RuntimeException{
    public TokenExpired(String message){
        super(message);
    }
}
