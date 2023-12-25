package com.products.service.elevatemartproductsservice.domain.enums;

public enum ProductVariables {
    ID("productId"),
    SKU("sku"),
    NAME("name"),
    TYPE("type"),
    ISTAXABLE("isTaxable"),
    PRICE("price"),
    IMAGEURL("imageUrl"),
    QUANTITIES("quantities"),
    THRESHOLDQUANTITIES("thresholdQuantities"),
    ISACTIVE("isActive");
    private final String value;
    public String getValue(){
       return this.value;
    }
    ProductVariables(String value){
        this.value=value;
    }
}
