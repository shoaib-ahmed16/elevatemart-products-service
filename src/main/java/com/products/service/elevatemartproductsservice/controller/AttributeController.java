package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.exception.AttributeNullObjectException;
import com.products.service.elevatemartproductsservice.services.AttributeService;
import com.products.service.elevatemartproductsservice.utils.Operation;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSuccess saveAttribute(@RequestBody Attribute attribute) throws AttributeNullObjectException{
        log.info("Received a Attribute Creation Request Call. Initiating Attribute Creation Process. :{}",attribute);
        if(Objects.isNull(attribute) || attribute.getName()==null){
            log.error("Failed to process request: Received an empty Attribute creation Object - {}", attribute);
            throw new AttributeNullObjectException("Attribute Creation. ");
        }
        attributeService.createAttribute(attribute);
        return  new ResponseSuccess(Operation.SAVED.getMessage(),Operation.ATTRIBUTE.getType()+Operation.SAVED.getOperation(),Operation.SAVED.getStatusCode());
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccess updateAttribute(@RequestBody Attribute attribute,@RequestParam("id") Long attributeId){
        log.info("Received a Attribute fields Updation Request Call. Initiating Attribute Updating Process. :{}",attribute);
        if(Objects.isNull(attribute) || Objects.isNull(attributeId)){
            log.error("Failed to process request: Received an empty Attribute creation Object - {}", attribute);
            throw new AttributeNullObjectException("Attribute Updation");
        }
        attributeService.updateAttribute(attribute,attributeId);
        return  new ResponseSuccess(Operation.UPDATE.getMessage(),Operation.ATTRIBUTE.getType()+Operation.UPDATE.getOperation(),Operation.UPDATE.getStatusCode());
    }

    @PutMapping("/updateStatus")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccess updateStatusAttribute(@RequestParam("id") Long  attributeId, @RequestParam("status") Boolean status){
        log.info("Received a Attribute Status update  Request Call. Initiating Attribute Status Updating Process. with Attribute Id:{}, Status :{}",attributeId,status);
        if(Objects.isNull(attributeId) || Objects.isNull(status)){
            log.error("Failed to process request: Received an empty Attribute Id :{} Or  Status field : {}",attributeId,status);
            throw new AttributeNullObjectException("Attribute Status Update");
        }
        attributeService.updateAttributeActiveStatus(attributeId,status);
        return  new ResponseSuccess(Operation.UPDATEACTIVESTATUS.getMessage(),Operation.ATTRIBUTE.getType()+Operation.UPDATEACTIVESTATUS.getOperation(),Operation.UPDATEACTIVESTATUS.getStatusCode());
    }

    @GetMapping("/fetchById")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccess fetchAttributeById(@RequestParam("id") Long  attributeId){
        log.info("Received a Attribute Detail Fetch By Id  Request Call. Initiating Attribute Fetching Process for  Attribute Id:{}",attributeId);
        if(Objects.isNull(attributeId)){
            log.error("Failed to process request: Received an empty Attribute Id :{}.",attributeId);
            throw new AttributeNullObjectException("Attribute Details Fetch By Id:"+attributeId);
        }
        return  new ResponseSuccess(Operation.FETCHBYID.getMessage(),Operation.ATTRIBUTE.getType()+Operation.FETCHBYID.getOperation(),Operation.FETCHBYID.getStatusCode(),attributeService.getAttribute(attributeId));
    }

    @GetMapping("/fetchAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccess fetchAllAttribute(){
        log.info("Received a All Attribute Detail Fetch Request Call. Initiating All Attribute Fetching Process");
        return  new ResponseSuccess(Operation.FETCHALL.getMessage(),Operation.ATTRIBUTE.getType()+Operation.FETCHALL.getOperation(),Operation.FETCHALL.getStatusCode(),attributeService.getAttributeList());
    }

    @DeleteMapping("/deleteById")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseSuccess deleteAttributeById(@RequestParam("id") Long attributeId){
        log.info("Received Request Call to delete the Attribute by Id: {}. Initiating Deleting Attribute record process.",attributeId);
        if(Objects.isNull(attributeId)){
            log.error("Failed to process request: Received an empty Attribute Id :{}.",attributeId);
            throw new AttributeNullObjectException("AttributeId is either Empty or ");
        }
        attributeService.deleteAttribute(attributeId);
        return  new ResponseSuccess(Operation.DELETEBYID.getMessage(),Operation.ATTRIBUTE.getType()+Operation.DELETEBYID.getOperation(),Operation.DELETEBYID.getStatusCode());
    }
    @DeleteMapping("/deleteMulitple")
    @ResponseStatus(HttpStatus.OK)
    public ResponseSuccess deleteMultipleAttribute(@RequestBody List<Long> attributeIds){
        log.info("Received Request Call to delete the Attribute by Ids: {}. Initiating Deleting Attribute record process.",attributeIds);
        if(Objects.isNull(attributeIds) && attributeIds.isEmpty()){
            log.error("Failed to process request: Received an empty Attribute Ids :{}.",attributeIds);
            throw new AttributeNullObjectException("AttributeId List is either Empty or ");
        }
        attributeService.deleteMultipleAttribute(attributeIds);
        return  new ResponseSuccess(Operation.DELETEMULTIPLE.getMessage(),Operation.ATTRIBUTE.getType()+Operation.DELETEMULTIPLE.getOperation(),Operation.DELETEMULTIPLE.getStatusCode());
    }

}
