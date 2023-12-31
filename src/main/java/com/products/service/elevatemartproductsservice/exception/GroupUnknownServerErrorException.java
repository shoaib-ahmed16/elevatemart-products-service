package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GroupUnknownServerErrorException extends RuntimeException{
    public  GroupUnknownServerErrorException(String message){
        super(message);
    }
}
