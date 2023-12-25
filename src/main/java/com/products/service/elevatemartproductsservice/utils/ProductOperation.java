package com.products.service.elevatemartproductsservice.utils;

public enum ProductOperation {

    SAVED("product-created",201,"Product is created and saved in database successFully!"),
    UPDATE("product-update",200,"Product details is Updated Successfully!"),

    DELETE("product-delete",204,"Product is deleted Successfully of Product Id: "),
    SKUUPDATE("product-sku-update",200,"Product Sku is updated Successfully for Product Id: "),
    QUANTITIESUPDATE("product-quantities-update",200,"Product quantities is updated  Successfully of Product sku: "),
    IMAGEUPDATE("product-image-update",200,"Product image url is  updated Successfully of Product sku: "),
    FOUNDBYSKU("product-found-by-sku",200,"Product is successfully fetch by product sku. Returning the Fetch product with this Response."),
    LISTBYSEARCHFILTER("product-list-searchfilter",200,"Product List is successfully fetch by Search filter. Returning the Fetch product list with this Response.");
    private String operation;
    private Integer statusCode;

    private String message;
    public Integer getStatusCode(){
        return this.statusCode;
    }
    public  String getOperation(){
        return  this.operation;
    }

    public  String getMessage(){
        return  this.message;
    }
    ProductOperation(String operation, Integer statusCode, String message){
        this.operation=operation;
        this.statusCode=statusCode;
        this.message=message;
    }
}

