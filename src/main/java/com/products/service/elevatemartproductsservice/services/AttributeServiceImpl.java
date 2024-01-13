package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.dto.AttributeDto;
import com.products.service.elevatemartproductsservice.dto.mappers.AttributeMapper;
import com.products.service.elevatemartproductsservice.exception.AttributeNotFoundException;
import com.products.service.elevatemartproductsservice.exception.AttributeUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.AttributeRepository;
import com.products.service.elevatemartproductsservice.utils.AttributeErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public final class AttributeServiceImpl implements AttributeService {

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
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+attribute);
        }
    }

    @Override
    public void updateAttribute(Attribute attribute,Long attributeId) {
        log.info("Initialize the  process of updating Attribute.");
        Attribute attr= attributeRepo.findById(attributeId).orElseThrow(()->{
            log.error(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attribute.getAttributeId());
            throw new AttributeNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId);
        });
        try{
            attr.setType(attribute.getType());
            attr.setName(attribute.getName());
            attr.setValue(attribute.getValue());
            attributeRepo.save(attr);
            log.info("Attribute fields is successfully updated and saved in the database :{}",attr);
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+attribute);
        }
    }

    @Override
    public void updateAttributeActiveStatus(Long attributeId, Boolean status) {
        log.info("Initialize the  process of fetching Attribute Details by Attribute Id: {}",attributeId);
        Attribute attr= attributeRepo.findById(attributeId).orElseThrow(()->{
            log.error(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId);
            throw new AttributeNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId);
        });
        log.info("Successfully fetched Attribute details for Attribute ID: {}", attributeId);
        try{
            log.info("Initialize the  process of updating Attribute Status from :{} to : {}",attr.isActive(),status);
            attr.setActive(status);
            attributeRepo.save(attr);
            log.info("Attribute status field is successfully updated to : {} and saved in the database",status);
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public AttributeDto getAttribute(Long attributeId) {
            log.info("Initialize the  process of fetching Attribute Details by Attribute Id: {}",attributeId);
            Attribute attr= attributeRepo.findById(attributeId).orElseThrow(()->{
                log.error(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId);
                throw new AttributeNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId);
            });
            log.info("Successfully fetched Attribute details for Attribute ID: {}", attributeId);
            log.info("Attribute Record found for the Attribute Id : {}. Returning the Attribute Object : {}",attributeId,attr);
            return AttributeMapper.convertToDto(attr);
    }

    @Override
    public List<AttributeDto> getAttributeList() {
        log.info("Initialize the  process of fetching All Attribute Details from the Database.");
        List<Attribute> attributeList = attributeRepo.findAll();
        if(attributeList.isEmpty()){
            log.error(AttributeErrorMessages.NORECORDSFOUND.getMessage());
            throw new AttributeNotFoundException(AttributeErrorMessages.NORECORDSFOUND.getMessage());
        }
        log.info("Successfully fetched All Attribute Details from the Database.");
        try{
            log.info("Initialize the process of Converting List<Attribute> to List<AttributeDto>  list:");
            var attributeDtos = new ArrayList<AttributeDto>();
            for(Attribute a:attributeList){
                attributeDtos.add(AttributeMapper.convertToDto(a));
            }
            log.info("Successfully Converted List<Attribute> to List<AttributeDto>  list");
            log.info("Returning the Attribute Record:{}",attributeDtos);
            return attributeDtos;
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void deleteAttribute(Long attributeId) {
        log.info("Initialize the  process of fetching Attribute Details by Attribute Id: {}",attributeId);
        Attribute attr= attributeRepo.findById(attributeId).orElseThrow(()->{
            log.error(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId);
            throw new AttributeNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+attributeId+". Please provide the correct Attribute Id to Delete the Attribute.");
        });
        log.info("Successfully fetched Attribute details for Attribute ID: {}", attributeId);
        try{
            log.info("Attribute Record found for the Attribute Id : {}. Deleting the Attribute Object : {}",attributeId,attr);
            attributeRepo.delete(attr);
            log.info("Attribute Record :{} Successfully deleted  for the Attribute Id : {}.",attr,attributeId);
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void deleteMultipleAttribute(List<Long> attributeIds) {
        try{
            Iterable<Long> iterator = attributeIds;
            log.info("Initialize the  process of fetching Attribute Details by Attribute Ids and deleting: {}",iterator);
            attributeRepo.deleteAllByIdInBatch(iterator);
            log.info("Attribute Records Successfully deleted  for the List of Attribute Ids : {}.",attributeIds);
        }
        catch (Exception exc){
            log.error(AttributeErrorMessages.UNKNOWNERROR.getMessage());
            throw new AttributeUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }
}
