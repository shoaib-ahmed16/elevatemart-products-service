package com.products.service.elevatemartproductsservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ProductErrorMessage {

    private static final ProductErrorMessage  productErrorMessage =new ProductErrorMessage();
    private  String message;
    private LocalDateTime localDateTime;
    private Integer httpStatus;
    private  String statusDescription;
    private String urlPath;

    private ProductErrorMessage(){

    }
    public synchronized static ProductErrorMessage getInstance(){
        return  productErrorMessage;
    }
}
