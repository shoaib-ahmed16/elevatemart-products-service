package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.exception.AttributeNullObjectException;
import com.products.service.elevatemartproductsservice.exception.ProductNullObjectException;
import com.products.service.elevatemartproductsservice.repository.AttributeRepository;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/attribute")
public class AttributeController {

    @Autowired
    private AttributeRepository attributeRepo;
    @PostMapping("/save")
    public ResponseSuccess saveAttribute(@RequestBody Attribute attribute){
        log.info("Received a Attribute Creation Request Call. Initiating Attribute Creation Process. :{}",attribute);
        if(Objects.isNull(attribute)){
            log.error("Failed to process request: Received an empty Attribute creation Object - {}", attribute);
            throw new AttributeNullObjectException("Attribute Creation");
        }
        return  null;
    }
}
