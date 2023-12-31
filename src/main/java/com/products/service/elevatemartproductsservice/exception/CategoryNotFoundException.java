package com.products.service.elevatemartproductsservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryNotFoundException extends  RuntimeException{

    public CategoryNotFoundException(String message){
        super(message);
    }
}
