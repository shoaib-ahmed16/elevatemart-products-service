package com.products.service.elevatemartproductsservice.utils;

public enum ProductErrorMessages {
    UNKNOWNERROR("Encountered an unknown server error while attempting to save the Product to the database."),

    NOTFOUNDBYSKU("Failed to find a product object using the provided SKU :"),
    NOTFOUNDBYID("Failed to find a product object using the provided product Id :");
    private String message;
    public String getMessage(){
        return this.message;
    }
    ProductErrorMessages(String message){
        this.message=message;
    }
}
