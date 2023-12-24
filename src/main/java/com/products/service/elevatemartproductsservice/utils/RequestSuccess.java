package com.products.service.elevatemartproductsservice.utils;

import com.products.service.elevatemartproductsservice.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSuccess {
    private String message;
    private String operation;
    private Integer statusCode;
    private ProductDto productDto;
    private List<ProductDto> dtoList;

    public RequestSuccess(String message,String operation,Integer statusCode){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
    }
    public RequestSuccess(String message,String operation,Integer statusCode,ProductDto productDto){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.productDto = productDto;
    }
    public RequestSuccess(String message,String operation,Integer statusCode,List<ProductDto> dtoList){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.dtoList=dtoList;
    }


}
