package com.products.service.elevatemartproductsservice.services;

import com.products.service.elevatemartproductsservice.domain.Category;
import com.products.service.elevatemartproductsservice.dto.CategoryDto;

import java.util.List;

public sealed interface CategoryService permits CategoryServiceImpl {
    void createCategory(Category category);

    void updateCategory(Long categoryId, Category category);

    CategoryDto getCategoryById(Long categoryId);
    List<CategoryDto> getAllCategorys();

    CategoryDto getCategoryByName(String categoryName);

    void updateCategoryActiveStatus(Long categoryId,Boolean status);

    void deleteCategoryById(Long categoryId);

    void deleteMultipleCategory(List<Long> categoryIds);



}
