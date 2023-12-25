package com.products.service.elevatemartproductsservice.utils;

import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.dto.SearchFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSuccess {
    private String message;
    private String operation;
    private Integer statusCode;
    private ProductDto productDto;
    private List<ProductDto> dtoList;

    private SearchFilter searchFilter;

    public ResponseSuccess(String message, String operation, Integer statusCode){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
    }
    public ResponseSuccess(String message, String operation, Integer statusCode, ProductDto productDto){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.productDto = productDto;
    }
    public ResponseSuccess(String message, String operation, Integer statusCode, List<ProductDto> dtoList){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.dtoList=dtoList;
    }
    public ResponseSuccess(String message, String operation, Integer statusCode, List<ProductDto> dtoList, SearchFilter searchFilter){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.dtoList=dtoList;
        this.searchFilter =searchFilter;
    }


}
