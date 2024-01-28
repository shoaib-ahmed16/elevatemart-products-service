package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Category;
import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.CategoryDto;
import com.products.service.elevatemartproductsservice.dto.ProductDto;
import com.products.service.elevatemartproductsservice.dto.mappers.CategoryMapper;
import com.products.service.elevatemartproductsservice.dto.mappers.ProductMapper;
import com.products.service.elevatemartproductsservice.exception.CategoryNotFoundException;
import com.products.service.elevatemartproductsservice.exception.CategoryUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.exception.ProductNotFoundException;
import com.products.service.elevatemartproductsservice.exception.ProductUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.ProductRepository;
import com.products.service.elevatemartproductsservice.utils.AttributeErrorMessages;
import com.products.service.elevatemartproductsservice.utils.CategoryErrorMessages;
import com.products.service.elevatemartproductsservice.utils.ProductErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public final class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public void createProduct(Product product) {
        try {
            log.info("Initializing product creation and database storage.");
            productRepo.save(product);
            log.info("The product is successfully created and saved in the database.");
        } catch (Exception exc) {
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void updateProductSku(Long productId, String updatedSku) {
        try {
            log.info("Initializing product sku updating.");
            Product prod = productRepo.findById(productId)
                    .orElseThrow(() -> {
                        log.error(ProductErrorMessages.NOTFOUNDBYID.getMessage() + productId);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYID.getMessage() + productId);
                    });
            log.info("Product Sku is updating from :{} to : {}.", prod.getSku(), updatedSku);
            prod.setSku(updatedSku);
            productRepo.save(prod);
            log.info("Product :{} updated successfully.", prod);
        } catch (Exception exc) {
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void updateProductQuantities(String sku, Long quantities) {
        try {
            log.info("Initializing product quantities updating.");
            Product prod = productRepo.findBySku(sku)
                    .orElseThrow(() -> {
                        log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() + sku);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() + sku);
                    });
            log.info("Product Quantities is updating from :{} to : {}.", prod.getQuantities(), prod.getQuantities() + quantities);
            prod.setQuantities(prod.getQuantities() + quantities);
            productRepo.save(prod);
            log.info("Product Quantities is :{} updated successfully.", prod.getQuantities());
        } catch (Exception exc) {
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void updateProductUrl(String sku, MultipartFile multipartFile) {

    }

    @Override
    public void updateProduct(Product product, String sku) {
        try {
            log.info("Initializing product updating.");
            Product prod = productRepo.findBySku(sku)
                    .orElseThrow(() -> {
                        log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() + product.getSku());
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() + product.getSku());
                    });
            log.info("Updated Product Object field data and saved changes to the database.");
            prod.setCategories(product.getCategories());
            prod.setName(product.getName());
            prod.setIsTaxable(product.getIsActive());
            prod.setTaxes(product.getTaxes());
            prod.setType(product.getType());
            productRepo.save(prod);
            log.info("Saved updated changes to the Product Object field in the database.");
        } catch (Exception exc) {
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }


    @Override
    public ProductDto getProductBySku(String sku) {
        try {
            log.info("Initializing product fetching process with Product Sku: {}.", sku);
            Product prod = productRepo.findBySku(sku)
                    .orElseThrow(() -> {
                        log.error(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() + sku);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYSKU.getMessage() + sku);
                    });
            log.info("Finished database search for product with Product Sku: {}", sku);
            log.info("The product :{} is Returning.", prod);
            return ProductMapper.convertToDto(prod);
        } catch (Exception exc) {
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        try{
            log.info("Initialize the  process of fetching All category Details from the Database.");
            List<Product> products = productRepo.findAll();
            if(products.isEmpty()){
                log.error(ProductErrorMessages.NORECORDSFOUND.getMessage());
                throw new ProductNotFoundException(ProductErrorMessages.NORECORDSFOUND.getMessage());
            }
            log.info("Successfully fetched All Product Details from the Database.");
            log.info("Initialize the process of Converting List<Product> to List<ProductDto>  list:");
            var productDtoList = new ArrayList<ProductDto>();
            for(Product a:products){
                productDtoList.add(ProductMapper.convertToDto(a));
            }
            log.info("Successfully Converted List<Product> to List<ProductDto>  list");
            log.info("Returning the category Record:{}",productDtoList);
            return productDtoList;
        }
        catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("Initializing product deletion process with Product Id: {}.", id);
            log.info("Initiating database search for product with ID: {}", id);
            Product prod = productRepo.findById(id)
                    .orElseThrow(() -> {
                        log.error(ProductErrorMessages.NOTFOUNDBYID.getMessage() + id);
                        throw new ProductNotFoundException(ProductErrorMessages.NOTFOUNDBYID.getMessage() + id);
                    });
            log.info("Finished database search for product with ID: {}", id);
            productRepo.delete(prod);
            log.info("The product :{} is Deleted Successfully.", prod);
        } catch (Exception exc) {
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage());
        }
    }

    @Override
    public void deleteMultipleProduct(List<Long> productIds) {
        try{
            Iterable<Long> iterator = productIds;
            log.info("Initialize the  process of fetching category Details by category Ids and deleting: {}",iterator);
            productRepo.deleteAllByIdInBatch(iterator);
            log.info("category Records Successfully deleted  for the List of category Ids : {}.",productIds);
        }
        catch (Exception exc){
            log.error(ProductErrorMessages.UNKNOWNERROR.getMessage());
            throw new ProductUnknownServerErrorException(ProductErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }
    /*

    @Override
    public List<ProductDto> getProductListBySearchFilter(SearchFilter searchFilter) {
        return productRepositoryCustom.findProductBySearchCriteria(searchFilter);
    }
    */
}


