package com.products.service.elevatemartproductsservice.exception;

public class TaxNullObjectException extends RuntimeException{

    private static String message =" Tax Cannot be Null!!!";
    public  TaxNullObjectException(String addOn){
        super(message+addOn);
    }
}
