package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryNullObjectException extends  RuntimeException{
    private static String message =" Group Cannot be Null!!!";
    public CategoryNullObjectException(String addOn){
        super(message+addOn);
    }
}
