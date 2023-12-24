package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long productId);

    void updateProductSku(Long productId, String updatedSku);
    void updateProductQuantities(String sku, Long quantities);
    void updateProductUrl(String sku, MultipartFile multipartFile);
    ProductDto getProductBySku(String sku);

}
