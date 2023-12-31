package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import com.products.service.elevatemartproductsservice.dto.AttributeDto;

import java.util.List;

public sealed interface AttributeService permits AttributeServiceImpl  {

    void createAttribute(Attribute attribute);
    void updateAttribute(Attribute attribute,Long attributeId);

    void updateAttributeActiveStatus(Long attributeId, Boolean status);
    AttributeDto getAttribute(Long attributeId);

    List<AttributeDto> getAttributeList();

    void deleteAttribute(Long attributeId);
    void deleteMultipleAttribute(List<Long> attributeIds);
}
