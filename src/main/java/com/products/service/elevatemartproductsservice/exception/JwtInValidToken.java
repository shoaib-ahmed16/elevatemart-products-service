package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtInValidToken extends RuntimeException {
    public JwtInValidToken(String message){
        super(message);
    }
}
