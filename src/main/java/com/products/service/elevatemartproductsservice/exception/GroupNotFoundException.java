package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GroupNotFoundException extends  RuntimeException{

    public GroupNotFoundException(String message){
        super(message);
    }
}
