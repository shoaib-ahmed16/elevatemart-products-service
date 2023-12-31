package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GroupNullObjectException extends  RuntimeException{
    private static String message =" Group Cannot be Null!!!";
    public  GroupNullObjectException(String addOn){
        super(message+addOn);
    }
}
