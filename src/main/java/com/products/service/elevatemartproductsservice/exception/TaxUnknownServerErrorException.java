package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaxUnknownServerErrorException extends RuntimeException{
    public  TaxUnknownServerErrorException(String message){
        super(message);
    }
}
