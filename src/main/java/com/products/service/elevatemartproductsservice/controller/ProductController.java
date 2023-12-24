package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.exception.ProductNullObjectException;
import com.products.service.elevatemartproductsservice.services.FileStorageService;
import com.products.service.elevatemartproductsservice.services.ProductService;
import com.products.service.elevatemartproductsservice.utils.Operation;
import com.products.service.elevatemartproductsservice.utils.RequestSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ProductService productService;


    @PostMapping("/uploadFile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
        return  ResponseEntity.ok()
                .body(String.valueOf(fileStorageService.storeFile(file)));

    }

    @PostMapping("save")
    public RequestSuccess createProduct(@RequestBody Product product){
        log.info("Received a Product Creation Request Call. Initiating Product Creation Process. :{}",product);
        if(Objects.isNull(product)){
            log.error("Received an empty Product creation Object: Unable to proceed with this request. {}");
            throw new ProductNullObjectException("Product Creation");
        }
        productService.createProduct(product);
        return new RequestSuccess("Product is Created Successfully!", Operation.PRODUCTSAVED.getValue(),Operation.PRODUCTSAVED.getStatusCode());
    }
}
