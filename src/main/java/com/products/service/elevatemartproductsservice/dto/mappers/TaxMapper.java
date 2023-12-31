package com.products.service.elevatemartproductsservice.dto.mappers;

import com.products.service.elevatemartproductsservice.domain.Tax;
import com.products.service.elevatemartproductsservice.dto.TaxDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class TaxMapper {

    private static final String taxEntityObj="Tax Entity Object";
    private static final String taxDtoObj="Tax Dto Object";
    private static final ModelMapper modelMapper = new ModelMapper();
    public static TaxDto convertToDto(Tax tax) {
        log.info("Utilizing ModelMapper to convert :{} into :{}.",taxEntityObj,taxDtoObj);
        return modelMapper.map(tax, TaxDto.class);
    }
    public static Tax convertToEntity(TaxDto taxDto) {
        log.info("Utilizing ModelMapper to convert :{} into :{}",taxDtoObj,taxEntityObj);
        return modelMapper.map(taxDto, Tax.class);
    }
}
