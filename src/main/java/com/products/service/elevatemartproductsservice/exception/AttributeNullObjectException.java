package com.products.service.elevatemartproductsservice.exception;

public class AttributeNullObjectException extends RuntimeException {
    private static String message =" Object Cannot be Null!!!";
    public AttributeNullObjectException(String addone){
        super(addone+message);
    }
}
