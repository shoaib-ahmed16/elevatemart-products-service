package com.products.service.elevatemartproductsservice.utils;

import com.products.service.elevatemartproductsservice.repository.AttributeRepository;

public enum AttributeOperation {
    SAVED("attribute-saved",201,"Arribute successfully saved in the database !");
    private String operation;
    private Integer statusCode;
    private String message;

    public String getOperation(){
       return this.operation;
    }
    public String getMessage(){
        return this.message;
    }
    public Integer getStatusCode(){
        return this.statusCode;
    }
    AttributeOperation(String operation,Integer statusCode,String message){
        this.message=message;
        this.statusCode=statusCode;
        this.operation=operation;
    }
}
