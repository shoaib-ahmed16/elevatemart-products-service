package com.products.service.elevatemartproductsservice.services.impl;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.dto.SearchFilter;
import com.products.service.elevatemartproductsservice.dto.mappers.ProductMapper;
import com.products.service.elevatemartproductsservice.exception.ProductNotFoundException;
import com.products.service.elevatemartproductsservice.exception.ProductUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.ProductRepository;
import com.products.service.elevatemartproductsservice.repository.customize.ProductRepositoryCustom;
import com.products.service.elevatemartproductsservice.services.ProductService;
import com.products.service.elevatemartproductsservice.utils.ProductErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductRepositoryCustom productRepositoryCustom;
    @Override
    public void createProduct(Product product) {
        try{
            log.info("Initializing product creation and database storage.");
                productRepo.save(product);
            log.info("The product is successfully created and saved in the database.");
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void updateProductSku(Long productId, String updatedSku) {
        try{
            log.info("Initializing product sku updating.");
            Optional<Product> prod = productRepo.findById(productId);
            if(prod.isEmpty()) {
                log.error(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
                throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
            }
            Product prodToUpdate =prod.get();
            log.info("Product Sku is updating from :{} to : {}.",prodToUpdate.getSku(),updatedSku);
            prodToUpdate.setSku(updatedSku);
            productRepo.save(prodToUpdate);
            log.info("Product :{} updated successfully.",prodToUpdate);
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void updateProductQuantities(String sku, Long quantities) {
        try{
            log.info("Initializing product quantities updating.");
            Optional<Product> prod = productRepo.findBySku(sku);
            if(prod.isEmpty()) {
                log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
                throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
            }
            Product prodToUpdate =prod.get();
            log.info("Product Quantities is updating from :{} to : {}.",prodToUpdate.getQuantities(),prodToUpdate.getQuantities()+quantities);
            prodToUpdate.setQuantities(prodToUpdate.getQuantities()+quantities);
            productRepo.save(prodToUpdate);
            log.info("Product Quantities is :{} updated successfully.",prodToUpdate.getQuantities());
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
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
                log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() +product.getSku());
                throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+product.getSku());
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
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        try{
            log.info("Initializing product deletion process with Product Id: {}.",productId);
            log.info("Initiating database search for product with ID: {}", productId);
            Optional<Product> prod =productRepo.findById(productId);
            log.info("Finished database search for product with ID: {}", productId);
            if(prod.isEmpty()){
                log.error(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
                throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
            }
            productRepo.delete(prod.get());
            log.info("The product :{} is Deleted Successfully.",prod.get());
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }

    }

    @Override
    public ProductDto getProductBySku(String sku) {
        try{
            log.info("Initializing product fetching process with Product Sku: {}.",sku);
            Optional<Product> prod =productRepo.findBySku(sku);
            log.info("Finished database search for product with Product Sku: {}", sku);
            if(prod.isEmpty()){
                log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
                throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
            }

            log.info("The product :{} is Returning.",prod.get());
            return ProductMapper.convertToDto(prod.get());
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public List<ProductDto> getProductListBySearchFilter(SearchFilter searchFilter) {
        return productRepositoryCustom.findProductBySearchCriteria(searchFilter);
    }
}
