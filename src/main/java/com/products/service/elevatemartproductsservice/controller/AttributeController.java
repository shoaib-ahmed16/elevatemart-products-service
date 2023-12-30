package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.exception.AttributeNullObjectException;
import com.products.service.elevatemartproductsservice.services.AttributeService;
import com.products.service.elevatemartproductsservice.utils.AttributeOperation;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;
    @PostMapping("/save")
    public ResponseSuccess saveAttribute(@RequestBody Attribute attribute){
        log.info("Received a Attribute Creation Request Call. Initiating Attribute Creation Process. :{}",attribute);
        if(Objects.isNull(attribute)){
            log.error("Failed to process request: Received an empty Attribute creation Object - {}", attribute);
            throw new AttributeNullObjectException("Attribute Creation");
        }
        attributeService.createAttribute(attribute);
        return  new ResponseSuccess(AttributeOperation.SAVED.getMessage(),AttributeOperation.SAVED.getOperation(),AttributeOperation.SAVED.getStatusCode());
    }

    @PostMapping("/update")
    public ResponseSuccess updateAttribute(@RequestBody Attribute attribute){
        log.info("Received a Attribute fields Updation Request Call. Initiating Attribute Updating Process. :{}",attribute);
        if(Objects.isNull(attribute)){
            log.error("Failed to process request: Received an empty Attribute creation Object - {}", attribute);
            throw new AttributeNullObjectException("Attribute Updation");
        }
        attributeService.updateAttribute(attribute);
        return  new ResponseSuccess(AttributeOperation.UPDATE.getMessage(),AttributeOperation.UPDATE.getOperation(),AttributeOperation.UPDATE.getStatusCode());
    }

    @PutMapping("/updateStatus")
    public ResponseSuccess updateStatusAttribute(@RequestParam("attributeId") Long  attributeId, @RequestParam("status") Boolean status){
        log.info("Received a Attribute Status update  Request Call. Initiating Attribute Status Updating Process. with Attribute Id:{}, Status :{}",attributeId,status);
        if(Objects.isNull(attributeId) && Objects.isNull(status)){
            log.error("Failed to process request: Received an empty Attribute Id :{} Or  Status field : {}",attributeId,status);
            throw new AttributeNullObjectException("Attribute Status Update");
        }
        attributeService.updateAttributeActiveStatus(attributeId,status);
        return  new ResponseSuccess(AttributeOperation.UPDATEACTIVESTATUS.getMessage(),AttributeOperation.UPDATEACTIVESTATUS.getOperation(),AttributeOperation.UPDATEACTIVESTATUS.getStatusCode());
    }

    @GetMapping("/fetchById")
    public ResponseSuccess fetchAttributeById(@RequestParam("attributeId") Long  attributeId){
        log.info("Received a Attribute Detail Fetch By Id  Request Call. Initiating Attribute Fetching Process for  Attribute Id:{}",attributeId);
        if(Objects.isNull(attributeId)){
            log.error("Failed to process request: Received an empty Attribute Id :{}.",attributeId);
            throw new AttributeNullObjectException("Attribute Details Fetch By Id:"+attributeId);
        }
        return  new ResponseSuccess(AttributeOperation.FETCHBYID.getMessage(),AttributeOperation.FETCHBYID.getOperation(),AttributeOperation.FETCHBYID.getStatusCode(),attributeService.getAttribute(attributeId));
    }

    @GetMapping("/fetchAll")
    public ResponseSuccess fetchAllAttribute(@RequestBody List<Long> attributeIds){
        log.info("Received a All Attribute Detail Fetch Request Call. Initiating All Attribute Fetching Process for  Attribute Ids:{}",attributeIds);
        if(Objects.isNull(attributeIds) && attributeIds.isEmpty()){
            log.error("Failed to process request: Received an empty Attribute Ids :{}.",attributeIds);
            throw new AttributeNullObjectException("AttributeIds List is either Empty or ");
        }
        return  new ResponseSuccess(AttributeOperation.FETCHALL.getMessage(),AttributeOperation.FETCHALL.getOperation(),AttributeOperation.FETCHALL.getStatusCode(),attributeService.getAttributeList());
    }

    @DeleteMapping("/deleteById")
    public ResponseSuccess deleteAttributeById(@RequestParam("attributeId") Long attributeId){
        log.info("Received Request Call to delete the Attribute by Id: {}. Initiating Deleting Attribute record process.",attributeId);
        if(Objects.isNull(attributeId)){
            log.error("Failed to process request: Received an empty Attribute Id :{}.",attributeId);
            throw new AttributeNullObjectException("AttributeId is either Empty or ");
        }
        attributeService.deleteAttribute(attributeId);
        return  new ResponseSuccess(AttributeOperation.DELETEBYID.getMessage(),AttributeOperation.DELETEBYID.getOperation(),AttributeOperation.DELETEBYID.getStatusCode());
    }
    @DeleteMapping("/deleteMulitple")
    public ResponseSuccess deleteMultipleAttribute(@RequestBody List<Long> attributeIds){
        log.info("Received Request Call to delete the Attribute by Ids: {}. Initiating Deleting Attribute record process.",attributeIds);
        if(Objects.isNull(attributeIds) && attributeIds.isEmpty()){
            log.error("Failed to process request: Received an empty Attribute Ids :{}.",attributeIds);
            throw new AttributeNullObjectException("AttributeId List is either Empty or ");
        }
        attributeService.deleteMultipleAttribute(attributeIds);
        return  new ResponseSuccess(AttributeOperation.DELETEMULTIPLE.getMessage(),AttributeOperation.DELETEMULTIPLE.getOperation(),AttributeOperation.DELETEMULTIPLE.getStatusCode());
    }

}
