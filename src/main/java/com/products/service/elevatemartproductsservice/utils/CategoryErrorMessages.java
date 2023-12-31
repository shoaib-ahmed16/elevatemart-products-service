package com.products.service.elevatemartproductsservice.utils;

public enum CategoryErrorMessages {
    UNKNOWNERROR("Encountered an unknown server error while attempting to save the Group to the database. Exception message: "),
    NOTFOUNDBYID("Failed to find a group object using the provided group Id :"),
    NORECORDSFOUND("Database table is empty. Please ensure there is at least one record present to reflect the result: No records found.");
    private String message;
    public String getMessage(){
        return this.message;
    }
    CategoryErrorMessages(String message){
        this.message=message;
    }
}
