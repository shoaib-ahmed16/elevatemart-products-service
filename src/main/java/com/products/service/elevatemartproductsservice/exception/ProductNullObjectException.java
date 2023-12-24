package com.products.service.elevatemartproductsservice.exception;

public class ProductNullObjectException extends  RuntimeException{
    private static String message =" Object Cannot be Null!!!";
    public ProductNullObjectException(String addone){
        super(addone+message);
    }
}
