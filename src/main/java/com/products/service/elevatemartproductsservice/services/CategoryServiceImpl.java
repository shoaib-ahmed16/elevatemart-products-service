package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Category;
import com.products.service.elevatemartproductsservice.dto.CategoryDto;
import com.products.service.elevatemartproductsservice.dto.mappers.CategoryMapper;
import com.products.service.elevatemartproductsservice.exception.CategoryNotFoundException;
import com.products.service.elevatemartproductsservice.exception.CategoryUnknownServerErrorException;
import com.products.service.elevatemartproductsservice.repository.CategoryRepository;
import com.products.service.elevatemartproductsservice.utils.AttributeErrorMessages;
import com.products.service.elevatemartproductsservice.utils.CategoryErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public final class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    @Override
    public void createCategory(Category category) {
        log.info("Initialize the process of creating Category and saving into the database.");
        try{
            categoryRepo.save(category);
            log.info("category is successfully save in the database :{}",category);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(CategoryErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+category);
        }
    }

    @Override
    public void updateCategory(Long categoryId, Category  category) {
        log.info("Initialize the  process of updating category.");
        try{
            Category grp= categoryRepo.findById(categoryId)
                    .orElseThrow(()->{
                        log.error(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+category.getCategoryId());
                        throw new CategoryNotFoundException(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+category.getCategoryId());
                    });
            grp.setProductList(category.getProductList());
            grp.setName(category.getName());
            grp.setAttributeList(category.getAttributeList());
            categoryRepo.save(grp);
            log.info("category fields is successfully updated and saved in the database :{}",grp);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(CategoryErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage()+" : "+category);
        }
    }

    @Override
    public void updateCategoryActiveStatus(Long categoryId, Boolean status) {
        log.info("Initialize the  process of updating category Status.");
        try{
            log.info("Initialize the  process of fetching category Details by category Id: {}",categoryId);
            Category grp= categoryRepo.findById(categoryId)
                    .orElseThrow(()->{
                        log.error(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryId);
                        throw new CategoryNotFoundException(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryId);
                    });
            log.info("Successfully fetched category details for category ID: {}", categoryId);
            log.info("category status field updating from :{} to : {} and saved in the database",grp.getIsActive(),status);
            grp.setIsActive(status);
            categoryRepo.save(grp);
            log.info("category status field is successfully updated to : {} and saved in the database",status);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(CategoryErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        try{
            log.info("Initialize the  process of fetching category Details by Attribute Id: {}",categoryId);
            Category grp= categoryRepo.findById(categoryId)
                    .orElseThrow(()->{
                        log.error(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryId);
                        throw new CategoryNotFoundException(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryId);
                    });
            log.info("Successfully fetch category details for category ID: {}", categoryId);
            log.info("category Record found for the category Id : {}. Returning the category Object : {}",categoryId,grp);
            return CategoryMapper.convertToDto(grp);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(CategoryErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public List<CategoryDto> getAllCategorys() {
        try{
            log.info("Initialize the  process of fetching All category Details from the Database.");
            List<Category> categoryList = categoryRepo.findAll();
            if(categoryList.isEmpty()){
                log.error(CategoryErrorMessages.NORECORDSFOUND.getMessage());
                throw new CategoryNotFoundException(CategoryErrorMessages.NORECORDSFOUND.getMessage());
            }
            log.info("Successfully fetched All category Details from the Database.");
            log.info("Initialize the process of Converting List<Category> to List<CategoryDto>  list:");
            var categoryDtoList = new ArrayList<CategoryDto>();
            for(Category a:categoryList){
                categoryDtoList.add(CategoryMapper.convertToDto(a));
            }
            log.info("Successfully Converted List<Category> to List<CategoryDto>  list");
            log.info("Returning the category Record:{}",categoryDtoList);
            return categoryDtoList;
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(CategoryErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public CategoryDto getCategoryByName(String categoryName) {
        try{
            log.info("Initialize the  process of fetching category Details by category Name: {}",categoryName);
            Category grp= categoryRepo.findByName(categoryName)
                    .orElseThrow(()->{
                        log.error(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryName);
                        throw new CategoryNotFoundException(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryName);
                    });
            log.info("Successfully fetch category details for category Name: {}", categoryName);
            log.info("category Record found for the category Name : {}. Returning the category Object : {}",categoryName,grp);
            return CategoryMapper.convertToDto(grp);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(CategoryErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }



    @Override
    public void deleteCategoryById(Long categoryId) {
        try{
            log.info("Initialize the  process of fetching category Details by category Id: {}",categoryId);
            Category grp= categoryRepo.findById(categoryId).orElseThrow(()->{
                log.error(CategoryErrorMessages.NOTFOUNDBYID.getMessage()+categoryId);
                throw new CategoryNotFoundException(AttributeErrorMessages.NOTFOUNDBYID.getMessage()+categoryId+". Please provide the correct category Id to Delete the category.");
            });
            log.info("Successfully fetched category details for category ID: {}", categoryId);
            log.info("category Record found for the category Id : {}. Deleting the category Object : {}",categoryId,grp);
            categoryRepo.delete(grp);
            log.info("category Record :{} Successfully deleted  for the category Id : {}.",grp,categoryId);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }

    @Override
    public void deleteMultipleCategory(List<Long> categoryIds) {
        try{
            Iterable<Long> iterator = categoryIds;
            log.info("Initialize the  process of fetching category Details by category Ids and deleting: {}",iterator);
            categoryRepo.deleteAllByIdInBatch(iterator);
            log.info("category Records Successfully deleted  for the List of category Ids : {}.",categoryIds);
        }
        catch (Exception exc){
            log.error(CategoryErrorMessages.UNKNOWNERROR.getMessage());
            throw new CategoryUnknownServerErrorException(AttributeErrorMessages.UNKNOWNERROR.getMessage()+exc.getMessage());
        }
    }
}
