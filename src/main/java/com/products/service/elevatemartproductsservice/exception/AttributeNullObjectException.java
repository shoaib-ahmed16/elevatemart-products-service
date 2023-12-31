package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AttributeNullObjectException extends RuntimeException {
    private static String message =" Attribute Cannot be Null!!!";
    public AttributeNullObjectException(String addone){
        super(addone+message);
    }
}
