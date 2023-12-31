package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.dto.SearchFilter;
import com.products.service.elevatemartproductsservice.dto.mappers.ProductMapper;
import com.products.service.elevatemartproductsservice.exception.ProductNotFoundException;
import com.products.service.elevatemartproductsservice.exception.ProductUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.ProductRepository;
import com.products.service.elevatemartproductsservice.repository.customize.ProductRepositoryCustom;
import com.products.service.elevatemartproductsservice.utils.ProductErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public final class ProductServiceImpl implements ProductService {
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
            Product prod = productRepo.findById(productId)
                    .orElseThrow(()->{
                        log.error(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
                    });
            log.info("Product Sku is updating from :{} to : {}.",prod.getSku(),updatedSku);
            prod.setSku(updatedSku);
            productRepo.save(prod);
            log.info("Product :{} updated successfully.",prod);
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void updateProductQuantities(String sku, Long quantities) {
        try{
            log.info("Initializing product quantities updating.");
            Product prod = productRepo.findBySku(sku)
                    .orElseThrow(()->{
                        log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
                    });
            log.info("Product Quantities is updating from :{} to : {}.",prod.getQuantities(),prod.getQuantities()+quantities);
            prod.setQuantities(prod.getQuantities()+quantities);
            productRepo.save(prod);
            log.info("Product Quantities is :{} updated successfully.",prod.getQuantities());
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void updateProductUrl(String sku, MultipartFile multipartFile) {

    }

    @Override
    public void updateProduct(Product product,String sku) {
        try{
            log.info("Initializing product updating.");
            Product prod = productRepo.findBySku(sku)
                    .orElseThrow(()->{
                        log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() +product.getSku());
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+product.getSku());
                    });
            log.info("Updated Product Object field data and saved changes to the database.");
            prod.setGroupList(product.getGroupList());
            prod.setName(product.getName());
            prod.setIsTaxable(product.getIsActive());
            prod.setTaxList(product.getTaxList());
            prod.setType(product.getType());
            productRepo.save(prod);
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
            Product prod =productRepo.findById(productId)
                    .orElseThrow(()->{
                        log.error(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYID.getMessage()+productId);
                    });
            log.info("Finished database search for product with ID: {}", productId);
            productRepo.delete(prod);
            log.info("The product :{} is Deleted Successfully.",prod);
        }catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }

    }

    @Override
    public ProductDto getProductBySku(String sku) {
        try{
            log.info("Initializing product fetching process with Product Sku: {}.",sku);
            Product prod =productRepo.findBySku(sku)
                    .orElseThrow(()->{
                        log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage()+sku);
                    });
            log.info("Finished database search for product with Product Sku: {}", sku);
            log.info("The product :{} is Returning.",prod);
            return ProductMapper.convertToDto(prod);
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
