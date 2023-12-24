package com.products.service.elevatemartproductsservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSuccess {

    private String message;
    private String operation;
    private Integer statusCode;

}
