package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Tax;
import com.products.service.elevatemartproductsservice.exception.CategoryNullObjectException;
import com.products.service.elevatemartproductsservice.exception.TaxNullObjectException;
import com.products.service.elevatemartproductsservice.services.TaxService;
import com.products.service.elevatemartproductsservice.utils.Operation;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/tax")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping("/save")
    public ResponseSuccess createTax(@RequestBody Tax tax){
        log.info("Received a Tax Creation Request Call. Initiating Tax Creation Process. :{}",tax);
        if(Objects.isNull(tax)){
            log.error("Failed to process request: Received an empty Tax creation Object - {}", tax);
            throw new TaxNullObjectException("Tax Creation");
        }
        taxService.create(tax);
        return  new ResponseSuccess(Operation.SAVED.getMessage(),Operation.TAX.getType()+Operation.SAVED.getOperation(),Operation.SAVED.getStatusCode());

    }

    @PostMapping("/update")
    public ResponseSuccess updateTax(@RequestBody Tax tax, @RequestParam("id") Long taxId){
        log.info("Received a Tax fields Updation Request Call. Initiating Tax Updating Process. :{}",tax);
        if(Objects.isNull(tax)){
            log.error("Failed to process request: Received an empty Tax creation Object - {}", tax);
            throw new TaxNullObjectException("Group Updation");
        }
        taxService.updateTax(tax,taxId);
        return  new ResponseSuccess(Operation.UPDATE.getMessage(),Operation.TAX.getType()+Operation.UPDATE.getOperation(),Operation.UPDATE.getStatusCode());
    }

    @PutMapping("/updatePercentage")
    public ResponseSuccess updatePercentageTax(@RequestParam("id") Long  taxId, @RequestParam("percentage") Double percentage){
        log.info("Received a Tax Percentage update  Request Call. Initiating Tax Percentage Updating Process. with Tax Id:{}, percentage :{}",taxId,percentage);
        if(taxId==0 || percentage <=0.0){
            log.error("Failed to process request: Received an empty Tax Id :{} Or  Percentage field : {}",taxId,percentage);
            throw new TaxNullObjectException("Group Status Update");
        }
        taxService.updateTaxPercentage(taxId,percentage);
        return  new ResponseSuccess(Operation.UPDATEPERCENTAGE.getMessage(),Operation.TAX.getType()+Operation.UPDATEPERCENTAGE.getOperation(),Operation.UPDATEPERCENTAGE.getStatusCode());
    }

    @PutMapping("/updateCode")
    public ResponseSuccess updateTaxCode(@RequestParam("id") Long  taxId, @RequestParam("code") String code){
        log.info("Received a Tax code update  Request Call. Initiating Tax code Updating Process. with Tax Id:{}, code :{}",taxId,code);
        if(taxId==0 || code.trim().equals("")){
            log.error("Failed to process request: Received an empty Tax Id :{} Or  code field : {}",taxId,code);
            throw new TaxNullObjectException("Group Status Update");
        }
        taxService.updateTaxCode(taxId,code);
        return  new ResponseSuccess(Operation.UPDATECODE.getMessage(),Operation.TAX.getType()+Operation.UPDATECODE.getOperation(),Operation.UPDATECODE.getStatusCode());
    }

    @GetMapping("/fetchById")
    public ResponseSuccess fetchTAxById(@RequestParam("id") Long  taxId){
        log.info("Received a Tax Detail Fetch By Id  Request Call. Initiating Tax Fetching Process for  Group Id:{}",taxId);
        if(taxId==0){
            log.error("Failed to process request: Received an invalid Tax Id :{}.",taxId);
            throw new CategoryNullObjectException("Tax Details Fetch By Id:"+taxId);
        }
        return  new ResponseSuccess(Operation.FETCHBYID.getMessage(),Operation.TAX.getType()+Operation.FETCHBYID.getOperation(),Operation.FETCHBYID.getStatusCode(),taxService.getTaxById(taxId));
    }

    @GetMapping("/fetchAll")
    public ResponseSuccess fetchTaxes(){
        log.info("Received a All Tax Detail Fetch Request Call. Initiating All Tax Fetching Process.");
        return  new ResponseSuccess(Operation.FETCHALL.getMessage(),Operation.TAX.getType()+Operation.FETCHALL.getOperation(),Operation.FETCHALL.getStatusCode(),taxService.getAllTax());
    }

    @DeleteMapping("/delete")
    public ResponseSuccess deleteCategoryById(@RequestParam("id") Long taxId){
        log.info("Received Request Call to delete the Tax by Id: {}. Initiating Deleting tax record process.",taxId);
        if(taxId!=0){
            log.error("Failed to process request: Received an empty tax Id :{}.",taxId);
            throw new CategoryNullObjectException("categoryId is either not valid or ");
        }
        taxService.deleteTaxById(taxId);
        return  new ResponseSuccess(Operation.DELETEBYID.getMessage(),Operation.TAX.getType()+Operation.DELETEBYID.getOperation(),Operation.DELETEBYID.getStatusCode());
    }



}
