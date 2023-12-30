package com.products.service.elevatemartproductsservice.repository.customize;

import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.dto.SearchFilter;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductDto> findProductBySearchCriteria(SearchFilter searchFilter);
}
