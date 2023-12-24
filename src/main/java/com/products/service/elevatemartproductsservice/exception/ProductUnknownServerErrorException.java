package com.products.service.elevatemartproductsservice.exception;

public class ProductUnknownServerErrorException extends RuntimeException{
    public ProductUnknownServerErrorException(String message){
        super(message);
    }
}
