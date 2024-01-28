package com.products.service.elevatemartproductsservice.utils;

public enum ProductErrorMessages {
    UNKNOWNERROR("Encountered an unknown server error while attempting to save the Product to the database."),

    NOTFOUNDBYSKU("Failed to find a product object using the provided SKU :"),
    NOTFOUNDBYID("Failed to find a product object using the provided product Id :"),

    NORECORDSFOUND("Database table is empty. Please ensure there is at least one record present to reflect the result: No records found.");
    private String message;
    public String getMessage(){
        return this.message;
    }
    ProductErrorMessages(String message){
        this.message=message;
    }
}
