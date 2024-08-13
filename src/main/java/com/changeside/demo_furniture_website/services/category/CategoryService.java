package com.changeside.demo_furniture_website.services.category;

import com.changeside.demo_furniture_website.models.dto.request.CategoryRequest;
import com.changeside.demo_furniture_website.models.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    void deleteCategory(Long id);

}
