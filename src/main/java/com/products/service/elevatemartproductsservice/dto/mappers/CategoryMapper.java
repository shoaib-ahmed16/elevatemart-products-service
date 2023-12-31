package com.products.service.elevatemartproductsservice.dto.mappers;

import com.products.service.elevatemartproductsservice.domain.Category;
import com.products.service.elevatemartproductsservice.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class CategoryMapper {

    private static final String categoryEntityObj="Group Entity Object";
    private static final String categoryDtoObj="Group Dto Object";
    private static final ModelMapper modelMapper = new ModelMapper();
    public static CategoryDto convertToDto(Category group) {
        log.info("Utilizing ModelMapper to convert :{} into :{}.",categoryEntityObj,categoryDtoObj);
        return modelMapper.map(group, CategoryDto.class);
    }
    public static Category convertToEntity(CategoryDto groupDto) {
        log.info("Utilizing ModelMapper to convert :{} into :{}",categoryDtoObj,categoryEntityObj);
        return modelMapper.map(groupDto, Category.class);
    }
}
