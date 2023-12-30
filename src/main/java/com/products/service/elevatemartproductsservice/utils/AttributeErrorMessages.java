package com.products.service.elevatemartproductsservice.utils;

public enum AttributeErrorMessages {

    UNKNOWNERROR("Encountered an unknown server error while attempting to save the Attribute to the database. Exception message: "),
    NOTFOUNDBYID("Failed to find a attribute object using the provided Attribute Id :"),
    NORECORDSFOUND("Database table is empty. Please ensure there is at least one record present to reflect the result: No records found.");
    private String message;
    public String getMessage(){
        return this.message;
    }
    AttributeErrorMessages(String message){
        this.message=message;
    }
}
