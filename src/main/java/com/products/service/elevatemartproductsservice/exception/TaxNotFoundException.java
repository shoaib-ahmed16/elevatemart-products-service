package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaxNotFoundException extends RuntimeException {
    public  TaxNotFoundException(String message){
        super(message);
    }
}
