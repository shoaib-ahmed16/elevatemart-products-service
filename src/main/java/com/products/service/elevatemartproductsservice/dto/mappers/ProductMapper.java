package com.products.service.elevatemartproductsservice.dto.mappers;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class ProductMapper {
    private static String productEntityObj=" Product Entity Object";
    private static String productDtoObj="Product Dto Object";
    private static final ModelMapper modelMapper = new ModelMapper();
    public ProductDto convertToDto(Product product) {
        log.info("Utilizing ModelMapper to convert :{} into :{}.",productEntityObj,productDtoObj);
        return modelMapper.map(product, ProductDto.class);
    }
    public Product convertToEntity(ProductDto productDto) {
        log.info("Utilizing ModelMapper to convert :{} into :{}",productDtoObj,productEntityObj);
        return modelMapper.map(productDto, Product.class);
    }
}
