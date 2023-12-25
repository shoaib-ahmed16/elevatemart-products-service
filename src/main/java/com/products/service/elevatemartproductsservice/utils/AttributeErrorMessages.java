package com.products.service.elevatemartproductsservice.utils;

public enum AttributeErrorMessages {

    UNKNOWNERROR("Encountered an unknown server error while attempting to save the Attribute to the database."),
    NOTFOUNDBYID("Failed to find a attribute object using the provided Attribute Id :");
    private String message;
    public String getMessage(){
        return this.message;
    }
    AttributeErrorMessages(String message){
        this.message=message;
    }
}
