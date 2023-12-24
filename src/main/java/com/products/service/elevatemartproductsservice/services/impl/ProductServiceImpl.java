package com.products.service.elevatemartproductsservice.services.impl;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.exception.ProductNotFoundException;
import com.products.service.elevatemartproductsservice.exception.ProductUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.ProductRepository;
import com.products.service.elevatemartproductsservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private static String saveProductError="Encountered an unknown server error while attempting to save the Product to the database.";
    private static String findProductError="Failed to locate a product object using the provided SKU value.";
    @Autowired
    private ProductRepository productRepo;
    @Override
    public void createProduct(Product product) {
        try{
            log.info("Initializing product creation and database storage.");
                productRepo.save(product);
            log.info("The product is successfully created and saved in the database.");
        }catch (Exception exc){
            log.error(saveProductError);
            throw new ProductUnknownServerErrorException(saveProductError);
        }
    }

    @Override
    public void updateProductSku(Long productId, String updatedSku) {
        try{
            log.info("Initializing product sku updating.");
            Optional<Product> prod = productRepo.findById(productId);
            if(prod.isEmpty()) {
                log.error(findProductError);
                throw new ProductNotFoundException(findProductError);
            }
            Product prodToUpdate =prod.get();
            log.info("Product Sku is updating Successfully  from :{} to : {}.",prodToUpdate.getSku(),updatedSku);
            prodToUpdate.setSku(updatedSku);
            productRepo.save(prodToUpdate);
            log.info("Product SKU updated successfully.");
        }catch (Exception exc){
            log.error(saveProductError);
            throw new ProductUnknownServerErrorException(saveProductError);
        }
    }

    @Override
    public void updateProductQuantities(String sku, Long quantities) {

    }

    @Override
    public void updateProductUrl(String sku, MultipartFile multipartFile) {

    }

    @Override
    public void updateProduct(Product product) {
        try{
            log.info("Initializing product updating.");
            Optional<Product> prod = productRepo.findBySku(product.getSku());
            if(prod.isEmpty()){
                log.error(findProductError);
                throw new ProductNotFoundException(findProductError);
            }
            log.info("Updated Product Object field data and saved changes to the database.");
                Product prodToUpdate =prod.get();
                prodToUpdate.setGroupList(product.getGroupList());
                prodToUpdate.setName(product.getName());
                prodToUpdate.setIsTaxable(product.getIsActive());
                prodToUpdate.setTaxList(product.getTaxList());
                prodToUpdate.setType(product.getType());
            productRepo.save(prodToUpdate);
            log.info("Saved updated changes to the Product Object field in the database.");
        }catch (Exception exc){
            log.error(saveProductError);
            throw new ProductUnknownServerErrorException(saveProductError);
        }
    }

    @Override
    public void deleteProduct(Integer productId) {

    }

    @Override
    public ProductDto getProductBySku(String sku) {
        return null;
    }
}
