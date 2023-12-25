package com.products.service.elevatemartproductsservice.services.impl;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.dto.AttributeDto;
import com.products.service.elevatemartproductsservice.exception.AttributeNotFoundException;
import com.products.service.elevatemartproductsservice.exception.AttributeUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.exception.ProductUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.AttributeRepository;
import com.products.service.elevatemartproductsservice.services.AttributeService;
import com.products.service.elevatemartproductsservice.utils.AttributeErrorMessages;
import com.products.service.elevatemartproductsservice.utils.ProductErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepo;

    @Override
    public void createAttribute(Attribute attribute) {
        log.info("Initialize the process of creating Attribute and saving into the database.");
        try{
            attributeRepo.save(attribute);
            log.info("Attribute is successfully save in the database :{}",attribute);
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+" : "+attribute);
        }
    }

    @Override
    public void updateAttribute(Attribute attribute) {
        log.info("Initialize the  process of updating Attribute.");
        try{
            Optional<Attribute> attr= attributeRepo.findById(attribute.getAttributeId());
            if(attr.isEmpty()){
                 log.error(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attribute.getAttributeId());
                 throw new AttributeNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attribute.getAttributeId());
            }
            Attribute attributeToUpdate= attr.get();
            attributeToUpdate.setType(attribute.getType());
            attributeToUpdate.setName(attribute.getName());
            attributeToUpdate.setValue(attribute.getValue());
            attributeRepo.save(attributeToUpdate);
            log.info("Attribute field is successfully updated and saved in the database :{}",attributeToUpdate);
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+" : "+attribute);
        }
    }

    @Override
    public void updateAttributeActiveStatus(Long attributeId, Boolean status) {

    }

    @Override
    public AttributeDto getAttribute(Long attributeId) {
        return null;
    }

    @Override
    public List<AttributeDto> getAttributeList() {
        return null;
    }

    @Override
    public void deleteAttribute(Long attributeId) {

    }

    @Override
    public void deleteMultipleAttribute(List<Long> attributeIds) {

    }
}
