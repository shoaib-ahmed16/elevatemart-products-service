package com.products.service.elevatemartproductsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaxDto {

    private Long taxId;
    private String type;
    private  String code;
    private String displayName;
    private Double percent;
}
