package com.products.service.elevatemartproductsservice.utils;

public enum AttributeOperation {
    SAVED("attribute-saved",201,"Attribute successfully saved in the database !"),
    UPDATE("attribute-update",200,"Attribute fields successfully updated in the database !"),
    UPDATEACTIVESTATUS("attribute-update-active-status",200,"Attribute status fields successfully updated in the database !"),
    FETCHBYID("attribute-fetchById",200,"Successfully fetch the Attribute By attribute ID. Returning the fetch record."),
    FETCHALL("attribute-fetchAll",200,"Successfully fetch the All Attribute . Returning the fetch Attribute list as record."),
    DELETEBYID("attribute-deleteById",204,"Attribute Record is deleted Successfully."),
    DELETEMULTIPLE("attribute-delete-multiple",204,"Multiple Attribute Record is deleted Successfully.");
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
