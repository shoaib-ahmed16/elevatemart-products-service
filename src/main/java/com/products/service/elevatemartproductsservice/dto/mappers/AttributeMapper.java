package com.products.service.elevatemartproductsservice.dto.mappers;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.dto.AttributeDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class AttributeMapper {

    private static final String attributeEntityObj=" Attribute Entity Object";
    private static final String attributeDtoObj="Attribute Dto Object";
    private static final ModelMapper modelMapper = new ModelMapper();
    public static AttributeDto convertToDto(Attribute attribute) {
        log.info("Utilizing ModelMapper to convert :{} into :{}.",attributeEntityObj,attributeDtoObj);
        return modelMapper.map(attribute, AttributeDto.class);
    }
    public static Attribute convertToEntity(AttributeDto attributeDto) {
        log.info("Utilizing ModelMapper to convert :{} into :{}",attributeDtoObj,attributeEntityObj);
        return modelMapper.map(attributeDto, Attribute.class);
    }
}
