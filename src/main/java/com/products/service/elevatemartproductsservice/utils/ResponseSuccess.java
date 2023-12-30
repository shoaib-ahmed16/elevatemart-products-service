package com.products.service.elevatemartproductsservice.utils;

import com.products.service.elevatemartproductsservice.dto.SearchFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSuccess<T> {
    private String message;
    private String operation;
    private Integer statusCode;
    private T responseDto;
    private List<T> responseList;
    private SearchFilter searchFilter;

    public ResponseSuccess(String message, String operation, Integer statusCode){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
    }
    public ResponseSuccess(String message, String operation, Integer statusCode, T responseDto){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.responseDto = responseDto;
    }
    public ResponseSuccess(String message, String operation, Integer statusCode, List<T> responseList){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.responseList=responseList;
    }
    public ResponseSuccess(String message, String operation, Integer statusCode, List<T> responseList, SearchFilter searchFilter){
        this.message=message;
        this.operation=operation;
        this.statusCode=statusCode;
        this.responseList=responseList;
        this.searchFilter =searchFilter;
    }

}
