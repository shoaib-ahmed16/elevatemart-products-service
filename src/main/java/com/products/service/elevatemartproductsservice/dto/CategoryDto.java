package com.products.service.elevatemartproductsservice.dto;

import com.products.service.elevatemartproductsservice.domain.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long groupId;

    private List<Attribute> attributeList;

    private Boolean isActive;

    private String name;
}
