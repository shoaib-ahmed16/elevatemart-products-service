package com.products.service.elevatemartproductsservice.utils;

import lombok.Data;

public enum Operation {

    PRODUCTSAVED("product-created",201),
    PRODUCTUPDATE("product-update",200),
    PRODUCTDELETE("product-delete",204);
    private String value;
    private Integer statusCode;
    public Integer getStatusCode(){
        return this.statusCode;
    }
    public  String getValue(){
        return  this.value;
    }
    Operation(String value,Integer statusCode){
        this.value=value;
        this.statusCode=statusCode;
    }
}

