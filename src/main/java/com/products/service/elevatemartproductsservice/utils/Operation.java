package com.products.service.elevatemartproductsservice.utils;

public enum Operation {
    PRODUCT("product"),
    GROUP("group"),
    ATTRIBUTE("attribute"),
    TAX("tax"),
    QUANTITIESUPDATE("product-quantities-update",200,"Product quantities is updated  Successfully of Product sku: "),

    IMAGEUPDATE("product-image-update",200,"Product image url is  updated Successfully of Product sku: "),
    FOUNDBYSKU("product-found-by-sku",200,"Product is successfully fetch by product sku. Returning the Fetch product with this Response."),
    LISTBYSEARCHFILTER("product-list-searchfilter",200,"Product List is successfully fetch by Search filter. Returning the Fetch product list with this Response."),
    SKUUPDATE("product-sku-update",200,"Product Sku is updated Successfully for Product Id: "),
    SAVED("-saved",201,"Successfully saved in the database !"),
    UPDATE("-update",200,"Successfully updated in the database !"),
    UPDATEPERCENTAGE("-update-percentage",200,"percentage fields successfully updated in the database !"),
    UPDATECODE("-update-code",200,"code fields successfully updated in the database !"),

    UPDATEACTIVESTATUS("-update-active-status",200,"status fields successfully updated in the database !"),
    FETCHBYID("-fetchById",200,"Successfully fetch the Record By Id. Returning the fetch record."),

    FETCHBYNAME("-fetchByName",200,"Successfully fetch the Record By Name. Returning the fetch record."),

    FETCHALL("-fetchAll",200,"Successfully fetch the All Records . Returning the fetch Records list as record."),
    DELETEBYID("-deleteById",204,"Record is deleted Successfully."),
    DELETEMULTIPLE("-delete-multiple",204,"Multiple Attribute Record is deleted Successfully.");
    private String operation;
    private String type;
    private Integer statusCode;
    private String message;

    public String getOperation(){
       return this.type+this.operation;
    }
    public String getMessage(){
        return this.message;
    }
    public Integer getStatusCode(){
        return this.statusCode;
    }
    public  String getType(){
       return this.type;
    }
    Operation(String type){
        this.type=type;
    }
    Operation(String operation,Integer statusCode,String message){
        this.message=message;
        this.statusCode=statusCode;
        this.operation=operation;
    }
}
