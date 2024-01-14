package com.products.service.elevatemartproductsservice.dto;

import com.products.service.elevatemartproductsservice.domain.Category;
import com.products.service.elevatemartproductsservice.domain.Tax;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private  Long productId;
    private String sku;
    private String name;
    private String type;
    private Boolean isTaxable;
    private String imageUrl;
    private List<Tax> taxes;
    private List<Category> categories;
    private Long quantities;
    private Long thresholdQuantities;
    private Boolean isActive;
}
