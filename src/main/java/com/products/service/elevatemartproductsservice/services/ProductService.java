package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public sealed interface ProductService  permits ProductServiceImpl  {
    void createProduct(Product product);
    void updateProduct(Product product, String sku);
    void updateProductSku(Long productId, String updatedSku);
    void updateProductQuantities(String sku, Long quantities);
    void updateProductUrl(String sku, MultipartFile multipartFile);
    ProductDto getProductBySku(String sku);

    List<ProductDto> getProducts();
    void delete(Long id);
    void deleteMultipleProduct(List<Long> productIds);


    /*
    List<ProductDto> getProductListBySearchFilter(SearchFilter searchFilter);
     */

}
