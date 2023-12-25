package com.products.service.elevatemartproductsservice.utils;

public enum Operation {

    PRODUCTSAVED("product-created",201,"Product is created and saved in database successFully!"),
    PRODUCTUPDATE("product-update",200,"Product details is Updated Successfully!"),

    PRODUCTDELETE("product-delete",204,"Product is deleted Successfully of Product Id: "),
    PRODUCTSKUUPDATE("product-sku-update",200,"Product Sku is updated Successfully for Product Id: "),
    PRODUCTQUANTITIESUPDATE("product-quantities-update",200,"Product quantities is updated  Successfully of Product sku: "),
    PRODUCTIMAGEUPDATE("product-image-update",200,"Product image url is  updated Successfully of Product sku: "),
    PRODUCTFOUNDBYSKU("product-found-by-sku",200,"Product is successfully fetch by product sku. Returning the Fetch product with this Response."),
    PRODUCTLISTBYSEARCHFILTER("product-list-searchfilter",200,"Product List is successfully fetch by Search filter. Returning the Fetch product list with this Response.");
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
    Operation(String operation,Integer statusCode,String message){
        this.operation=operation;
        this.statusCode=statusCode;
        this.message=message;
    }
}

