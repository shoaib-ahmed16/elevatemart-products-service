package com.products.service.elevatemartproductsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImpFieldDto {
    private String sku;
    private Long productId;

    private Long quantities;
    private MultipartFile file;

}
