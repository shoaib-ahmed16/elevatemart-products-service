package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Product;
import com.products.service.elevatemartproductsservice.dto.ProductImpFieldDto;
import com.products.service.elevatemartproductsservice.dto.SearchFilter;
import com.products.service.elevatemartproductsservice.exception.ProductNullObjectException;
import com.products.service.elevatemartproductsservice.services.FileStorageService;
import com.products.service.elevatemartproductsservice.services.ProductService;
import com.products.service.elevatemartproductsservice.utils.ProductOperation;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ProductService productService;


    @PostMapping("/uploadFile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){
        return  ResponseEntity.ok()
                .body(String.valueOf(fileStorageService.storeFile(file)));

    }

    @PostMapping("/save")
    public ResponseSuccess createProduct(@RequestBody Product product){
        log.info("Received a Product Creation Request Call. Initiating Product Creation Process. :{}",product);
        if(Objects.isNull(product)){
            log.error("Failed to process request: Received an empty Product creation Object - {}", product);
            throw new ProductNullObjectException("Product Creation");
        }
        productService.createProduct(product);
        return new ResponseSuccess(ProductOperation.SAVED.getMessage(), ProductOperation.SAVED.getOperation(), ProductOperation.SAVED.getStatusCode());
    }
    @PostMapping("/update")
    public ResponseSuccess updateProduct(@RequestBody Product product, @RequestParam("sku") String sku){
        log.info("Received a Product Updating Request Call. Initiating Product Creation Process. :{}",product);
        if(Objects.isNull(product)){
            log.error("Failed to process product update: Received an empty Product object. Unable to proceed. Product: {}", product);
            throw new ProductNullObjectException("Product Updating");
        }
        productService.updateProduct(product,sku);
        return new ResponseSuccess(ProductOperation.UPDATE.getMessage(), ProductOperation.UPDATE.getOperation(), ProductOperation.UPDATE.getStatusCode());
    }

    @PutMapping("/updateSku")
    public ResponseSuccess updateProductSku(@RequestBody ProductImpFieldDto prodFields){
        log.info("Received a Request Call to update the Sku of Product. Initiating Product Sku updation Process. :{}",prodFields);
        if(Objects.isNull(prodFields)){
            log.error("Failed to update ProductImpFieldDto. The received object for updating is empty: {}. Cannot proceed with the request.", prodFields);
            throw new ProductNullObjectException("ProductImpFieldDto updating");
        }
        productService.updateProductSku(prodFields.getProductId(), prodFields.getSku());
        return new ResponseSuccess(ProductOperation.SKUUPDATE.getMessage()+prodFields.getProductId(), ProductOperation.SKUUPDATE.getOperation(), ProductOperation.SKUUPDATE.getStatusCode());
    }

    @PutMapping("/updateQuantities")
    public ResponseSuccess updateProductQuantities(@RequestBody ProductImpFieldDto prodFields){
        log.info("Received a Request Call to update the Sku of Product. Initiating Product Sku updation Process. :{}",prodFields);
        if(Objects.isNull(prodFields)){
            log.error("Failed to update ProductImpFieldDto. Received an empty updation Object (prodFields). Unable to proceed.");
            throw new ProductNullObjectException("ProductImpFieldDto updating");
        }
        productService.updateProductQuantities(prodFields.getSku(),prodFields.getQuantities());
        return new ResponseSuccess(ProductOperation.QUANTITIESUPDATE.getMessage()+prodFields.getSku(), ProductOperation.QUANTITIESUPDATE.getOperation(), ProductOperation.QUANTITIESUPDATE.getStatusCode());
    }

    @PutMapping("/updateImageUrl")
    public ResponseSuccess updateProductImageUrl(@RequestBody ProductImpFieldDto prodFields){
        log.info("Received a Request Call to update the Sku of Product. Initiating Product Sku updation Process. :{}",prodFields);
        if(Objects.isNull(prodFields)){
            log.error("Failed to update ProductImpFieldDto. The received object is empty: {}. Unable to proceed with the request.", prodFields);
            throw new ProductNullObjectException("ProductImpFieldDto updating");
        }
        productService.updateProductUrl(prodFields.getSku(),prodFields.getFile());
        return new ResponseSuccess(ProductOperation.IMAGEUPDATE.getMessage()+prodFields.getSku(), ProductOperation.IMAGEUPDATE.getOperation(), ProductOperation.IMAGEUPDATE.getStatusCode());
    }
    @GetMapping("/productBySku")
    public ResponseSuccess getProductById(@RequestParam String productSku){
        log.info("Initiating Product retrieval by SKU: {}", productSku);
        if(Objects.isNull(productSku)|| productSku.equals("")){
            log.error("Received an empty or null value for 'productSku' :{}. Unable to proceed with this request.",productSku);
            throw new ProductNullObjectException("ProductImpFieldDto updating");
        }
        return new ResponseSuccess(ProductOperation.FOUNDBYSKU.getMessage(), ProductOperation.FOUNDBYSKU.getOperation(), ProductOperation.FOUNDBYSKU.getStatusCode(),productService.getProductBySku(productSku));
    }
    @GetMapping("/productList/searchFilter")
    public ResponseSuccess getProductListBySearchCriteria(@RequestBody SearchFilter searchFilter){
        log.info("Initiating Product List retrieval by searchFilter: {}", searchFilter);
        if(Objects.isNull(searchFilter) && Objects.isNull(searchFilter.getFilterGroup())
                && Objects.isNull(searchFilter.getFilterGroup().getFilterFieldsList())
                && searchFilter.getFilterGroup().getFilterFieldsList().size()==0
        ){
            log.error("Received an empty or null value for 'searchFilter or it's internal Object Body' :{}. Unable to proceed with this request.",searchFilter);
            throw new ProductNullObjectException("ProductImpFieldDto updating");
        }
        return new ResponseSuccess(ProductOperation.LISTBYSEARCHFILTER.getMessage(), ProductOperation.LISTBYSEARCHFILTER.getOperation(), ProductOperation.LISTBYSEARCHFILTER.getStatusCode(),productService.getProductListBySearchFilter(searchFilter),searchFilter);

    }

    @DeleteMapping("/delete")
    public ResponseSuccess deleteProduct(@RequestParam Long productId){
        log.info("Received a Request Call to delete Product. Initiating Product Sku updation Process. :{}",productId);
        if(ObjectUtils.isEmpty(productId) || productId==0){
            log.error("Failed to proceed with the request. ProductId not received: {}", productId);
            throw new ProductNullObjectException("Not Received any Product Id for deleting the Product. ");
        }
        productService.deleteProduct(productId);
        return new ResponseSuccess(ProductOperation.DELETE.getMessage()+productId, ProductOperation.DELETE.getOperation(), ProductOperation.DELETE.getStatusCode());

    }
}
