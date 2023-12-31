package com.products.service.elevatemartproductsservice.services;


import com.products.service.elevatemartproductsservice.domain.Tax;
import com.products.service.elevatemartproductsservice.dto.TaxDto;
import com.products.service.elevatemartproductsservice.dto.mappers.TaxMapper;
import com.products.service.elevatemartproductsservice.exception.GroupNotFoundException;
import com.products.service.elevatemartproductsservice.exception.TaxNotFoundException;
import com.products.service.elevatemartproductsservice.exception.TaxUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.TaxRepository;
import com.products.service.elevatemartproductsservice.utils.AttributeErrorMessages;
import com.products.service.elevatemartproductsservice.utils.GroupErrorMessages;
import com.products.service.elevatemartproductsservice.utils.TaxErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public final class TaxServiceImpl implements TaxService {

    @Autowired
    private TaxRepository taxRepo;
    @Override
    public void create(Tax tax) {
        log.info("Initialize the process of creating Tax and saving into the database.");
        try{
            taxRepo.save(tax);
            log.info("Tax is successfully save in the database :{}",tax);
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+tax);
        }
    }

    @Override
    public void updateTaxPercentage(Long taxId, Double percentage) {
        log.info("Initialize the  process of updating Tax percentage.");
        try{
            Tax tax= taxRepo.findById(taxId)
                    .orElseThrow(()->{
                        log.error(TaxErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                        throw new TaxNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                    });
            tax.setPercent(percentage);
            taxRepo.save(tax);
            log.info("Tax Percentage  is successfully updated and saved in the database :{}",tax);
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+taxId);
        }
    }

    @Override
    public void updateTaxCode(Long taxId, String code) {
        log.info("Initialize the  process of updating Tax code.");
        try{
            Tax tax= taxRepo.findById(taxId)
                    .orElseThrow(()->{
                        log.error(TaxErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                        throw new TaxNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                    });
            tax.setCode(code);
            taxRepo.save(tax);
            log.info("Tax Code is successfully updated and saved in the database :{}",tax);
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+taxId);
        }
    }

    @Override
    public void updateTax(Tax updateTax, Long taxId) {
        log.info("Initialize the  process of updating Tax details.");
        try{
            Tax tax= taxRepo.findById(taxId)
                    .orElseThrow(()->{
                        log.error(TaxErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                        throw new TaxNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                    });
            tax.setType(updateTax.getType());
            tax.setPercent(updateTax.getPercent());
            tax.setDisplayName(updateTax.getDisplayName());
            tax.setCode(updateTax.getCode());
            taxRepo.save(tax);
            log.info("Tax details is successfully updated and saved in the database :{}",tax);
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+taxId);
        }

    }

    @Override
    public TaxDto getTaxById(Long taxId) {
        try{
            log.info("Initialize the  process of fetching Group Details by Attribute Id: {}",taxId);
            Tax tax= taxRepo.findById(taxId)
                    .orElseThrow(()->{
                        log.error(TaxErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                        throw new TaxNotFoundException(GroupErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                    });
            log.info("Successfully fetch Tax details for Tax ID: {}", taxId);
            log.info("Tax Record found for the Tax Id : {}. Returning the Tax Object : {}",taxId,tax);
            return TaxMapper.convertToDto(tax);
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public List<TaxDto> getAllTax() {
        try{
            log.info("Initialize the  process of fetching All Tax Details from the Database.");
            List<Tax> taxList = taxRepo.findAll();
            if(taxList.isEmpty()){
                log.error(TaxErrorMessages.NORECORDSFOUND.getMessage());
                throw new TaxNotFoundException(TaxErrorMessages.NORECORDSFOUND.getMessage());
            }
            log.info("Successfully fetched All Tax Details from the Database.");
            log.info("Initialize the process of Converting List<Tax> to List<TaxDto>  list:");
            var taxDtos = new ArrayList<TaxDto>();
            for(Tax a:taxList){
                taxDtos.add(TaxMapper.convertToDto(a));
            }
            log.info("Successfully Converted List<Tax> to List<TaxDto>  list");
            log.info("Returning the Tax Record:{}",taxDtos);
            return taxDtos;
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void deleteTaxById(Long taxId) {
        try{
            log.info("Initialize the  process of fetching Tax Details by Tax Id: {}",taxId);
            Tax tax= taxRepo.findById(taxId).orElseThrow(()->{
                log.error(GroupErrorMessages.NOTFOUNDBYID.getMessage()+taxId);
                throw new GroupNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+taxId+". Please provide the correct Group Id to Delete the Group.");
            });
            log.info("Successfully fetched Tax details for Tax ID: {}", taxId);
            log.info("Group Record found for the Tax Id : {}. Deleting the Tax Object : {}",taxId,tax);
            taxRepo.delete(tax);
            log.info("Tax Record :{} Successfully deleted  for the tax Id : {}.",tax,taxId);
        }
        catch (Exception exc){
            log.error(TaxErrorMessages.UNKNOWNERROR.getMessage());
            throw new TaxUnknownServerErrorException(TaxErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }
}
