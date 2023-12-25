package com.products.service.elevatemartproductsservice.dto;

import com.products.service.elevatemartproductsservice.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeDto {
    private  Long attributeId;
    private String type;
    private String value;
    private String name;
    private Boolean active;
}
