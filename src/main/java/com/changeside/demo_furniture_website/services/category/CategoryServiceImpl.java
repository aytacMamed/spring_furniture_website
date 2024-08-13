package com.changeside.demo_furniture_website.services.category;

import com.changeside.demo_furniture_website.exception.CategoryNotFoundException;
import com.changeside.demo_furniture_website.models.dto.request.CategoryRequest;
import com.changeside.demo_furniture_website.models.dto.response.CategoryResponse;
import com.changeside.demo_furniture_website.models.entity.Category;
import com.changeside.demo_furniture_website.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return modelMapper.map(category, CategoryResponse.class);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        // CategoryRequest'ten Category nesnesi oluştur
        Category category = modelMapper.map(categoryRequest, Category.class);

        // Kategoriyi veritabanına kaydet
        Category savedCategory = categoryRepository.save(category);

        // Kaydedilen Category nesnesini CategoryResponse'e dönüştür
        CategoryResponse categoryResponse = modelMapper.map(savedCategory, CategoryResponse.class);

        return categoryResponse;
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        // Kategoriyi bul
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // categoryRequest'ten gelen verilerle mevcut Category nesnesini güncelle
        modelMapper.map(categoryRequest, category);

        // Güncellenmiş kategoriyi kaydet
        Category updatedCategory = categoryRepository.save(category);

        // Güncellenmiş kategoriyi CategoryResponse'e dönüştür
        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }


    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }
}
