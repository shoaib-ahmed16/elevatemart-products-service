package com.products.service.elevatemartproductsservice.controller;

import com.products.service.elevatemartproductsservice.domain.Category;
import com.products.service.elevatemartproductsservice.exception.CategoryNullObjectException;
import com.products.service.elevatemartproductsservice.services.CategoryService;
import com.products.service.elevatemartproductsservice.utils.Operation;
import com.products.service.elevatemartproductsservice.utils.ResponseSuccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/save")
    public ResponseSuccess createCategory(@RequestBody Category category){
        log.info("Received a category Creation Request Call. Initiating Attribute Creation Process. :{}",category);
        if(Objects.isNull(category)){
            log.error("Failed to process request: Received an empty category creation Object - {}", category);
            throw new CategoryNullObjectException("Category Creation");
        }
        categoryService.createCategory(category);
        return  new ResponseSuccess(Operation.SAVED.getMessage(),Operation.CATEGORY.getType()+Operation.SAVED.getOperation(),Operation.SAVED.getStatusCode());

    }

    @PostMapping("/update")
    public ResponseSuccess updateCategory(@RequestBody Category category, @RequestParam("id") Long categoryId){
        log.info("Received a category fields Updation Request Call. Initiating category Updating Process. :{}",category);
        if(Objects.isNull(category) || categoryId==0){
            log.error("Failed to process request: Received an empty category creation Object - {}", category);
            throw new CategoryNullObjectException("Category Updation");
        }
        categoryService.updateCategory(categoryId,category);
        return  new ResponseSuccess(Operation.UPDATE.getMessage(),Operation.CATEGORY.getType()+Operation.UPDATE.getOperation(),Operation.UPDATE.getStatusCode());
    }

    @PutMapping("/updateStatus")
    public ResponseSuccess updateStatusCategory(@RequestParam("id") Long  categoryId, @RequestParam("status") Boolean status){
        log.info("Received a category Status update  Request Call. Initiating category Status Updating Process. with category Id:{}, Status :{}",categoryId,status);
        if(Objects.isNull(categoryId) && Objects.isNull(status)){
            log.error("Failed to process request: Received an empty category Id :{} Or  Status field : {}",categoryId,status);
            throw new CategoryNullObjectException("Category Status Update");
        }
        categoryService.updateCategoryActiveStatus(categoryId,status);
        return  new ResponseSuccess(Operation.UPDATEACTIVESTATUS.getMessage(),Operation.CATEGORY.getType()+Operation.UPDATEACTIVESTATUS.getOperation(),Operation.UPDATEACTIVESTATUS.getStatusCode());
    }

    @GetMapping("/fetchById")
    public ResponseSuccess fetchCategoryById(@RequestParam("id") Long  categoryId){
        log.info("Received a category Detail Fetch By Id  Request Call. Initiating category Fetching Process for  category Id:{}",categoryId);
        if(categoryId ==0){
            log.error("Failed to process request: Received an empty category Id :{}.",categoryId);
            throw new CategoryNullObjectException("category Details Fetch By Id:"+categoryId);
        }
        return  new ResponseSuccess(Operation.FETCHBYID.getMessage(),Operation.CATEGORY.getType()+Operation.FETCHBYID.getOperation(),Operation.FETCHBYID.getStatusCode(),categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/fetchByName")
    public ResponseSuccess fetchCategoryByName(@RequestParam("name") String  categoryName){
        log.info("Received a category Detail Fetch By Id  Request Call. Initiating category Fetching Process for  category Name:{}",categoryName);
        if(Objects.isNull(categoryName) || categoryName.trim().equals("")){
            log.error("Failed to process request: Received an empty category Name :{}.",categoryName);
            throw new CategoryNullObjectException("category Details Fetch By Name:"+categoryName);
        }
        return  new ResponseSuccess(Operation.FETCHBYNAME.getMessage(),Operation.CATEGORY.getType()+Operation.FETCHBYNAME.getOperation(),Operation.FETCHBYNAME.getStatusCode(),categoryService.getCategoryByName(categoryName));
    }

    @GetMapping("/fetchAll")
    public ResponseSuccess fetchAllCategory(){
        log.info("Received a All category Detail Fetch Request Call. Initiating All category Fetching Process.");
        return  new ResponseSuccess(Operation.FETCHALL.getMessage(),Operation.CATEGORY.getType()+Operation.FETCHALL.getOperation(),Operation.FETCHALL.getStatusCode(),categoryService.getAllCategorys());
    }

    @DeleteMapping("/deleteById")
    public ResponseSuccess deleteCategoryById(@RequestParam("id") Long categoryId){
        log.info("Received Request Call to delete the category by Id: {}. Initiating Deleting category record process.",categoryId);
        if(categoryId!=0){
            log.error("Failed to process request: Received an empty category Id :{}.",categoryId);
            throw new CategoryNullObjectException("categoryId is either not valid or ");
        }
        categoryService.deleteCategoryById(categoryId);
        return  new ResponseSuccess(Operation.DELETEBYID.getMessage(),Operation.CATEGORY.getType()+Operation.DELETEBYID.getOperation(),Operation.DELETEBYID.getStatusCode());
    }

    @DeleteMapping("/deleteMulitple")
    public ResponseSuccess deleteMultipleCategory(@RequestBody List<Long> categoryIds){
        log.info("Received Request Call to delete the category by Ids: {}. Initiating Deleting category record process.",categoryIds);
        if(Objects.isNull(categoryIds) && categoryIds.isEmpty()){
            log.error("Failed to process request: Received an empty category Ids :{}.",categoryIds);
            throw new CategoryNullObjectException("categoryIds List is either Empty or ");
        }
        categoryService.deleteMultipleCategory(categoryIds);
        return  new ResponseSuccess(Operation.DELETEMULTIPLE.getMessage(),Operation.CATEGORY.getType()+Operation.DELETEMULTIPLE.getOperation(),Operation.DELETEMULTIPLE.getStatusCode());
    }

}
